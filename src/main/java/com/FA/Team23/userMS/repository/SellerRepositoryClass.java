package com.FA.Team23.userMS.repository;

import org.springframework.data.repository.CrudRepository;

import com.FA.Team23.userMS.entity.SellerClass;

public interface SellerRepositoryClass extends CrudRepository<SellerClass, String> {
	
	public SellerClass findByPhoneNumber(String phoneNumber);
	
	public SellerClass findByEmail(String email);
	
	public SellerClass findBySellerId(String id);

}