package com.agora.api;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.agora.api.controller.AgoraShoppingController;
import com.agora.api.controller.dto.BillResponse;
import com.agora.api.controller.dto.OrderRequest;
import com.agora.api.controller.dto.ProductItemRequest;
import com.agora.api.controller.dto.ProductItemResponse;
import com.agora.api.data.AgoraProductRepository;
import com.agora.api.model.Product;
import com.agora.api.service.IAgoraShoppingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

@RunWith(SpringRunner.class)
@WebMvcTest(AgoraShoppingController.class)
class AgoraShoppingApplicationTests {
	
	@Autowired
	private MockMvc mockMVC;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@MockBean
	private IAgoraShoppingService agoraBusinessService;
	
	@MockBean
	private AgoraProductRepository agoraBusinessData;

	@Test
	void testRetrieveAllProducts() throws Exception {
		
		List<Product> productList = new ArrayList<Product>();
		
		Product product = new Product();
		product.setProductId(1);
		product.setProductName("Meiji");
		product.setUnits("ml");
		product.setProductPrice(new BigDecimal(5.5));
		product.setProductSize(250);
		
		productList.add(product);
		
		when(agoraBusinessService.retrieveAllProducts()).thenReturn(productList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/agora/products");
		
		mockMVC.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().json("[{productId:1,productName:Meiji}]"))
				.andReturn();
		
	}
	
	@Test
	void testPlaceOrder() throws Exception {
		
		OrderRequest orderRequest = new OrderRequest();
		List<ProductItemRequest> productItemList = new ArrayList<ProductItemRequest>();
		ProductItemRequest item= new ProductItemRequest(100,0.5f);
		productItemList.add(item);
		orderRequest.setSelectedItemList(productItemList);

		String jsonInput = toJson(orderRequest);
		
		BillResponse billResponse = new BillResponse();
		
		ProductItemResponse productResponse = new ProductItemResponse();
		productResponse.setTotalNumberOfItems(1);
		productResponse.setActualAmountToPay(new BigDecimal(10));
		productResponse.setTotalAmount(new BigDecimal(10));
		
		billResponse.setOrderId("1");
		billResponse.setProductItemResponse(productResponse);
		
		String jsonOutput = toJson(ImmutableMap.of("noOfItems",1,"orderId",1));
		
		when(agoraBusinessService.processOrder(any(OrderRequest.class))).thenReturn(billResponse);

		mockMVC.perform(post("/agora/order").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonInput)).
				andDo(print()).
				andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).
				andExpect(status().isOk()).
				andExpect(jsonPath("$.orderId", is("1")));
	
	}
	
	private String toJson(Object o) throws Exception {
        return objectMapper.writeValueAsString(o);
    }
	
}
