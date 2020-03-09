package com.agora.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.agora.api.controller.dto.OrderRequest;
import com.agora.api.controller.dto.ProductItem;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AgoraShoppingApplicationIT {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testRetriveAllAproducts() throws JSONException {
		
		String response = this.restTemplate.getForObject("/agora/products", String.class);
		JSONAssert.assertEquals("[{productId:100},{productId:101},{productId:102},{productId:103},{productId:104}]",response,false);
		
	}
	
	@Test
	public void testPlaceOrderForOfferDiscountFivePercent() throws JSONException {
		
		OrderRequest orderRequest = new OrderRequest();
		
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		ProductItem productItem = new ProductItem();
		productItem.setItemId(100);
		productItem.setQuantity(0.5f);
		productItemList.add(productItem);
		
		orderRequest.setSelectedItemList(productItemList);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity("/agora/order", orderRequest, String.class);
		JSONAssert.assertEquals("{offerApplied:DISC5,discountApplied:5}",response.getBody(),false);
		
	}
	
	@Test
	public void testPlaceOrderForOfferDiscountTenPercent() throws JSONException {
		
		OrderRequest orderRequest = new OrderRequest();
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		ProductItem productItemWithOfferTenPercentDiscount= new ProductItem(104,1.5f);
		productItemList.add(productItemWithOfferTenPercentDiscount);
		orderRequest.setSelectedItemList(productItemList);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity("/agora/order", orderRequest, String.class);
		JSONAssert.assertEquals("{offerApplied:DISC10,discountApplied:10}",response.getBody(),false);
		
	}
	
	@Test
	public void testPlaceOrderForOfferFreeBox() throws JSONException {
		
		OrderRequest orderRequest = new OrderRequest();
		
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		ProductItem productItemWithFreeBoxOffer= new ProductItem(103,0.5f);
		productItemList.add(productItemWithFreeBoxOffer);
		orderRequest.setSelectedItemList(productItemList);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity("/agora/order", orderRequest, String.class);
		JSONAssert.assertEquals("{offerApplied:FREEBOX,discountApplied:0}",response.getBody(),false);
		
	}
	
	@Test
	public void testPlaceOrderBuyOneGetOneOffer() throws JSONException {
		
		OrderRequest orderRequest = new OrderRequest();
		
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		ProductItem productItemWithBuyOneGetOneOffer= new ProductItem(101,0.5f);
		productItemList.add(productItemWithBuyOneGetOneOffer);
		orderRequest.setSelectedItemList(productItemList);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity("/agora/order", orderRequest, String.class);
		JSONAssert.assertEquals("{offerApplied:B1G1,discountApplied:0,noOfItems:2}",response.getBody(),false);
		
	}
	
	@Test
	public void testPlaceOrderProductWithNoOffer() throws JSONException {
		
		OrderRequest orderRequest = new OrderRequest();
		
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		ProductItem productItemWithNoOffer= new ProductItem(102,0.5f);
		productItemList.add(productItemWithNoOffer);
		orderRequest.setSelectedItemList(productItemList);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity("/agora/order", orderRequest, String.class);
		JSONAssert.assertEquals("{offerApplied:NOOFFER,discountApplied:0,noOfItems:1}",response.getBody(),false);
		
	}
	
	@Test
	public void testPlaceOrderForDuplicateItem() throws Exception {
		
		OrderRequest orderRequest = new OrderRequest();
		
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		ProductItem itemOne= new ProductItem(100,0.5f);
		productItemList.add(itemOne);
		
		ProductItem duplicateItem = new ProductItem(100,0.5f);
		productItemList.add(duplicateItem);
		
		orderRequest.setSelectedItemList(productItemList);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity("/agora/order", orderRequest, String.class);
		JSONAssert.assertEquals("{noOfItems:1,actualAmountToPay:7.000}",response.getBody(),false);
		
	}
	
	@Test
	public void testPlaceOrderForInvalidItem() throws Exception {
		
		OrderRequest orderRequest = new OrderRequest();
		
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		ProductItem itemOne= new ProductItem(100123,0.5f);
		productItemList.add(itemOne);
		
		orderRequest.setSelectedItemList(productItemList);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity("/agora/order", orderRequest, String.class);
		JSONAssert.assertEquals("{errorResponse:[{errorCode:ERR001}]}",response.getBody(),false);
		
	}
	
	@Test
	public void testPlaceOrderForInvalidQuantity() throws Exception {
		
		OrderRequest orderRequest = new OrderRequest();
		
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		ProductItem itemOne= new ProductItem(100,0.25f);
		productItemList.add(itemOne);
		
		orderRequest.setSelectedItemList(productItemList);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity("/agora/order", orderRequest, String.class);
		JSONAssert.assertEquals("{errorResponse:[{errorCode:ERR002}]}",response.getBody(),false);
		
	}

}
