package com.FA.Team23.userMS.service;

import java.util.List;

import com.FA.Team23.userMS.dto.BuyerDTOClass;
import com.FA.Team23.userMS.dto.CartDTOClass;
import com.FA.Team23.userMS.dto.LoginDTOClass;
import com.FA.Team23.userMS.dto.SellerDTOClass;
import com.FA.Team23.userMS.exception.UserExceptionClass;

public interface UserServiceClass {

	
	public String buyerRegist(BuyerDTOClass buyerDTO) throws UserExceptionClass;
	
	public String sellerRegist(SellerDTOClass sellerDTO) throws UserExceptionClass;
	
	public String buyerLogin(LoginDTOClass login) throws UserExceptionClass;
	
	public String sellerLogin(LoginDTOClass login) throws UserExceptionClass;
	
	public String deleteTheBuyer(String id);
	
	public String deleteTheSeller(String id);
	
	public String wishlistService(String prodId,String buyerId);
	
	public String cartService(String prodId, String buyerId, Integer quantity);
	
	public List<CartDTOClass> getTheCartProducts(String id) throws UserExceptionClass;
	
	public String removeFromTheCart(String buyerId, String prodId) throws UserExceptionClass;
	
	public String updateTheRewardPoint(String buyerId, Integer rewPoints) throws UserExceptionClass;
	
}

