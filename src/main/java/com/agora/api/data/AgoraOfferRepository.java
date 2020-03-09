package com.agora.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agora.api.model.Offer;

@Repository
public interface AgoraOfferRepository extends JpaRepository<Offer,Integer> {

}
