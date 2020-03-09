package com.agora.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.agora.api.controller.dto.ProductItem;
import com.agora.api.data.AgoraProductRepository;
import com.agora.api.model.Product;
import com.agora.api.service.AgoraShoppingService;
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
	private AgoraShoppingService agoraBusinessService;
	
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
		
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		
		ProductItem productItem = new ProductItem();
		productItem.setItemId(1);
		productItem.setQuantity(0.5f);
		
		productItemList.add(productItem);
		
		String jsonInput = toJson(ImmutableMap.of("selectedItemList",productItemList));
		
		BillResponse billResponse = new BillResponse();
		billResponse.setNoOfItems(1);
		billResponse.setActualAmountToPay(new BigDecimal(10));
		billResponse.setDiscountApplied(new BigDecimal(0));
		billResponse.setOfferApplied(null);
		billResponse.setAmountAfterDiscount(new BigDecimal(10));
		billResponse.setOrderId("1");
		
		String jsonOutput = toJson(ImmutableMap.of("noOfItems",1,"orderId",1));
		
		when(agoraBusinessService.processOrder(productItemList)).thenReturn(billResponse);

		mockMVC.perform(post("/agora/order").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonInput)).
				andDo(print()).
				andExpect(status().isOk());
		
	}
	
	private String toJson(Object o) throws Exception {
        return objectMapper.writeValueAsString(o);
    }
	
}
