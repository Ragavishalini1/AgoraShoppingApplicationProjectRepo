package com.agora.api.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agora.api.controller.dto.BillResponse;
import com.agora.api.controller.dto.OrderRequest;
import com.agora.api.data.AgoraProductRepository;
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

@Controller
@RequestMapping("/testAgora")
public class AgoraShoppingWebPageController {
	
	@Autowired
	private IAgoraShoppingService agoraBusinessService;
	
	@Autowired
	private AgoraProductRepository agoraRepository;
	
	/**
	 * @return List<Product> - Retuens List of Products
	 */
	@GetMapping("/hello")
	public String retrieveAllProducts(Map<String, Object> model) {
		
		List<Product> listOfProducts = agoraBusinessService.retrieveAllProducts();
		
		model.put("message", "Customer");
		model.put("productList", listOfProducts);
		
		return "index";
		
	}
	
	@GetMapping("/hello/{productId}")
	public String retrieveSpecificProduct(@PathVariable("productId") String productId,Map<String, Object> model) {
		
		if(productId != null) {
		
		Product product = agoraRepository.findByProductId(Integer.parseInt(productId));
		
		model.put("message", product.getProductName());
		model.put("productPrice", product.getProductPrice());
		model.put("productQuantity", product.getProductSize());
		}
		
		return "product";
		
	}

}
