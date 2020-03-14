package com.agora.api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.agora.api.data.AgoraInventoryRepository;
import com.agora.api.data.AgoraProductRepository;
import com.agora.api.model.Inventory;
import com.agora.api.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AgoraJPATest {
	
	@Autowired
    private AgoraInventoryRepository inventoryRepo;
	
	@Autowired
    private AgoraProductRepository productRepo;
 
    @Test
    @Transactional
    public void testQueryMethod() {
        Inventory inventory = inventoryRepo.findByProductIdProductName("MEIJI MILK");
        
        assertEquals(inventory.getInventoryId(),1);
    }
    
    
    @Test
    @Transactional
    public void testProductRepo() {
        List<Product> products = productRepo.findFirst2ByProductPriceOrderByProductPriceAsc(new BigDecimal(3.50));
        
        assertEquals(products.size(),2);
    }
    
    @Test
    @Transactional
    public void testProductPriceGreater() {
        List<Product> products = productRepo.findByProductPriceGreaterThan(new BigDecimal(2.50));
        
        assertEquals(products.size(),3);
    }
    
    @Test
    @Transactional
    public void testProductPriceLess() {
        List<Product> products = productRepo.findByProductPriceLessThan(new BigDecimal(3.50));
        
        assertEquals(products.size(),2);
    }

}
