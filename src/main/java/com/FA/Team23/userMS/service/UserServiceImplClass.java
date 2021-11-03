package com.FA.Team23.userMS.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FA.Team23.userMS.dto.BuyerDTOClass;
import com.FA.Team23.userMS.dto.CartDTOClass;
import com.FA.Team23.userMS.dto.LoginDTOClass;
import com.FA.Team23.userMS.dto.SellerDTOClass;
import com.FA.Team23.userMS.entity.BuyerClass;
import com.FA.Team23.userMS.entity.CartClass;
import com.FA.Team23.userMS.entity.SellerClass;
import com.FA.Team23.userMS.entity.WishlistClass;
import com.FA.Team23.userMS.exception.UserExceptionClass;
import com.FA.Team23.userMS.repository.BuyerRepositoryClass;
import com.FA.Team23.userMS.repository.CartRepositoryClass;
import com.FA.Team23.userMS.repository.SellerRepositoryClass;
import com.FA.Team23.userMS.repository.WishlistRepositoryClass;
import com.FA.Team23.userMS.utility.PrimaryKey;
import com.FA.Team23.userMS.validator.ValidateClass;

@Service(value = "userServiceClass")
@Transactional
public class UserServiceImplClass implements UserServiceClass {
	
	
	private static int bIndex;
	private static int sIndex;
	
	static {
		bIndex=100;
		sIndex=100;
	}
	
	@Autowired
	private BuyerRepositoryClass buyerRepo;
	
	@Autowired
	private SellerRepositoryClass sellerRepo;

	@Autowired
	private WishlistRepositoryClass wishlistRepo;
	
	@Autowired
	private CartRepositoryClass cartRepo;
	
	
	//This is method for buyerRegistration
	@Override
	public String buyerRegist(BuyerDTOClass buyerDTO) throws UserExceptionClass {
		// TODO Auto-generated method stub
		
		ValidateClass.validateTheBuyer(buyerDTO); // name , phonenumber , email , pass
		
		BuyerClass buy = buyerRepo.findByEmail(buyerDTO.getEmail()); // return  xyz@gmail.com
		BuyerClass buy1 = buyerRepo.findByPhoneNumber(buyerDTO.getPhoneNumber()); //null 9
		if(buy != null || buy1 != null)
			throw new UserExceptionClass("Buyer already present");
		
		String id = "B" + bIndex++;  //"B100" , "B101",
		
		buy = new BuyerClass(); // Entity 
		
		buy.setBuyerId(id);
		buy.setEmail(buyerDTO.getEmail());
		buy.setName(buyerDTO.getName());
		buy.setPhoneNumber(buyerDTO.getPhoneNumber());
		buy.setPassword(buyerDTO.getPassword());
		buy.setIsActive("False");
		buy.setIsPrivileged("False");
		buy.setRewardPoints("0");
		
		buyerRepo.save(buy);
		
		return buy.getBuyerId();
	}

	
	//This method for sellerRegist
	@Override
	public String sellerRegist(SellerDTOClass sellerDTO) throws UserExceptionClass {
		// TODO Auto-generated method stub
		
		ValidateClass.validateTheSeller(sellerDTO);
		
		SellerClass sel = sellerRepo.findByPhoneNumber(sellerDTO.getPhoneNumber());
		
		if(sel != null)
			throw new UserExceptionClass("Seller Already present");
		
		String id = "S" + sIndex++;
		
		sel = new SellerClass();
		
		sel.setEmail(sellerDTO.getEmail());
		sel.setSellerId(id);
		sel.setName(sellerDTO.getName());
		sel.setPassword(sellerDTO.getPassword());
		sel.setIsActive("False");
		sel.setPhoneNumber(sellerDTO.getPhoneNumber());
		
		sellerRepo.save(sel);
		
		return sel.getSellerId();
	}

	//this is method for buyer login
	@Override
	public String buyerLogin(LoginDTOClass login) throws UserExceptionClass {
		// TODO Auto-generated method stub
		
		String email = login.getEmailId();
		String Password = login.getPassword();
		if(!ValidateClass.validateTheEmail(email)) // post
			throw new UserExceptionClass("Enter valid email id");
		
		BuyerClass buyer = buyerRepo.findByEmail(email);
		
		
		if(buyer == null)
			throw new UserExceptionClass("Wrong credentials");
		if(!buyer.getPassword().equals(Password) ) {
			
			//System.out.println(buyer);
			
			throw new UserExceptionClass("Wrong credentials");
		}
		
		buyer.setIsActive("True");
			
		buyerRepo.save(buyer);
		
		return "Login Success";
	}

	//this is method for seller login
	@Override
	public String sellerLogin(LoginDTOClass login) throws UserExceptionClass {

		String email = login.getEmailId();
		String password = login.getPassword();
		if(!ValidateClass.validateTheEmail(email))
			throw new UserExceptionClass("Enter valid email id");
		
		
		
		SellerClass sell = sellerRepo.findByEmail(email);
		
//		System.out.println(seller);
		
		if(sell == null)
			throw new UserExceptionClass("Wrong credentials");
		
		if(!sell.getPassword().equals(password))
			throw new UserExceptionClass("Wrong credentials");
		
		sell.setIsActive("True");
			
		sellerRepo.save(sell);
		
		return "Login Success";
	}

	//this is deleting the buyer
	@Override
	public String deleteTheBuyer(String id){
		
		BuyerClass buy = buyerRepo.findByBuyerId(id);
		
		buyerRepo.delete(buy);
		
		return "Account successfully deleted";
	}

	//deleting the seller
	@Override
	public String deleteTheSeller(String id) {
		
		SellerClass sell = sellerRepo.findBySellerId(id);
		
		sellerRepo.delete(sell);
		
		return "Account successfully deleted";
	}

	//Adding to Wishlist
	@Override
	public String wishlistService(String prodId, String buyerId) {
		
		PrimaryKey customer = new PrimaryKey(prodId,buyerId);
	
		WishlistClass wish = new WishlistClass();
		
		wish.setCustomId(customer);
		
		wishlistRepo.save(wish);
		
		return "Added Successfully to Wishlist";
	}
	
	//Adding to service Cart
	@Override
	public String cartService(String prodId, String buyerId, Integer quantity) {
		
		PrimaryKey customer = new PrimaryKey(prodId,buyerId);
	
		CartClass cart = new CartClass();
		
		cart.setCustomPK(customer);
		
		cart.setQuantity(quantity);
		
		cartRepo.save(cart);
		
		return "Added Successfully to Cart";
	}

	//Getting the list of product in cart
	@Override
	public List<CartDTOClass> getTheCartProducts(String id) throws UserExceptionClass {
		
		List<CartClass> lst = cartRepo.findByCustomPKBuyerId(id);
		
		if(lst.isEmpty())
			throw new UserExceptionClass("No Items In Cart");
		
		List<CartDTOClass> li = new ArrayList<>();
		
		for(CartClass cart : lst)
		{
			CartDTOClass cartDTO = new CartDTOClass();
			
			cartDTO.setBuyerId(cart.getCustomPK().getBuyerId());
			cartDTO.setProdId(cart.getCustomPK().getProdId());
			cartDTO.setQuantity(cart.getQuantity());
			
			li.add(cartDTO);
		}
		
		return li;
	}

	//Removing from the list
	@Override
	public String removeFromTheCart(String buyerId, String prodId) throws UserExceptionClass {
		// TODO Auto-generated method stub
		
		CartClass cart = cartRepo.findByCustomPKBuyerIdAndCustomPKProdId(buyerId, prodId);
		
		if(cart==null)
			throw new UserExceptionClass("No Such Item In Cart");
		
		cartRepo.deleteByCustomPKBuyerIdAndCustomPKProdId(buyerId, prodId);
		
		return "The product was deleted successfully";
	}

	//Update the rewardPoint
	@Override
	public String updateTheRewardPoint(String buyerId, Integer rewPoints) throws UserExceptionClass {
		// TODO Auto-generated method stub
		
		BuyerClass buy = buyerRepo.findByBuyerId(buyerId);
		
		if(buy==null)
			throw new UserExceptionClass("Buyer not found");
		
		buy.setRewardPoints(rewPoints.toString());
		
		buyerRepo.save(buy);
		
		return "Reward Points Updated for buyer Id : "+ buy.getBuyerId();
	}

}