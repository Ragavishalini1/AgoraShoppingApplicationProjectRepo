package com.agora.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agora.api.controller.dto.BillResponse;
import com.agora.api.controller.dto.OrderRequest;
import com.agora.api.model.Product;
import com.agora.api.service.IAgoraShoppingService;

/**
 * 
 * @author ragavishalini
 * 
 * The AgoraBusinessController serves as the entry point for the below requests
 * 
 * /products - To view all the available products from the catalog with their prescribed quantity and Price
 * 
 * /order - User can place order by providing Item Id and the desired quantity
 *
 */

@RestController
@RequestMapping("/agora")
public class AgoraShoppingController {
	
	@Autowired
	private IAgoraShoppingService agoraBusinessService;
	
	/**
	 * @return List<Product> - Retuens List of Products
	 */
	@GetMapping("/products")
	public List<Product> retrieveAllProducts() {
		
		List<Product> listOfProducts = agoraBusinessService.retrieveAllProducts();
		
		return listOfProducts;
		
	}
	
	/**
	 * Return Product By Id
	 * 
	 * @param productId
	 * @return
	 */
	@GetMapping("/products/{productId}")
	public Product retrieveProductById(@PathVariable("productId") String productId) {
		
		Product product = agoraBusinessService.retrieveProductByProductId(Integer.parseInt(productId));
		
		return product;
		
	}
	
	/**
	 * 
	 * @param orderRequest - Item Id and Quantity
	 * @return BillResponse
	 */
	@PostMapping("/order")
	@ResponseBody
	public BillResponse placeOrder(@RequestBody OrderRequest orderRequest) {
		
		BillResponse billResponse = agoraBusinessService.processOrder(orderRequest);
		
		return billResponse;
		
	}

}
