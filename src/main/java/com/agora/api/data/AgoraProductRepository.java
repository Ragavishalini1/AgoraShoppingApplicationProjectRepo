package com.agora.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agora.api.model.Product;

@Repository
public interface AgoraProductRepository extends JpaRepository<Product,Integer> {

}
