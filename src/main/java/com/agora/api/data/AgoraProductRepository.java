package com.agora.api.data;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agora.api.model.Product;

@Repository
public interface AgoraProductRepository extends JpaRepository<Product,Integer> {
	
	public List<Product> findFirst2ByProductPriceOrderByProductPriceAsc(BigDecimal productPrice);
	
	public List<Product> findByProductPriceOrderByProductPriceAsc(BigDecimal productPrice);
	
	public List<Product> findByProductPriceGreaterThan(BigDecimal productPrice);
	
	public List<Product> findByProductPriceLessThan(BigDecimal productPrice);
	
	public Product findByProductId(int productId);

}
