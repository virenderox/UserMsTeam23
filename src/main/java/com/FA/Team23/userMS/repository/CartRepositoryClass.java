package com.FA.Team23.userMS.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.FA.Team23.userMS.entity.CartClass;
import com.FA.Team23.userMS.utility.PrimaryKey;

public interface CartRepositoryClass extends CrudRepository<CartClass, PrimaryKey> {
	
	
	public List<CartClass> findByCustomPKBuyerId(String id); 
	
	public void deleteByCustomPKBuyerIdAndCustomPKProdId(String buyId,String prodId);
	
	public CartClass findByCustomPKBuyerIdAndCustomPKProdId(String buyId,String ProdId);

}
