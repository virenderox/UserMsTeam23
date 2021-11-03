package com.FA.Team23.userMS.repository;

import org.springframework.data.repository.CrudRepository;

import com.FA.Team23.userMS.entity.BuyerClass;

public interface BuyerRepositoryClass extends CrudRepository<BuyerClass, String> {
	
	public BuyerClass findByPhoneNumber(String phoneNumber);
	
	public BuyerClass findByEmail(String email);
	
	public BuyerClass findByBuyerId(String id);

}
