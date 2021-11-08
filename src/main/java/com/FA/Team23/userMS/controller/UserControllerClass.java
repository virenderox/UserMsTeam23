package com.FA.Team23.userMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.FA.Team23.userMS.dto.BuyerDTOClass;
import com.FA.Team23.userMS.dto.CartDTOClass;
import com.FA.Team23.userMS.dto.LoginDTOClass;
import com.FA.Team23.userMS.dto.ProductDTOClass;
import com.FA.Team23.userMS.dto.SellerDTOClass;
import com.FA.Team23.userMS.exception.UserExceptionClass;
import com.FA.Team23.userMS.service.UserServiceClass;

@RestController
@CrossOrigin
public class UserControllerClass {
	
	@Autowired
	private UserServiceClass userService;
	
	@Autowired
	Environment environment;
	
	@Value("${product.uri}")
	String productUri;
	
	//to register the  buyer 
	@RequestMapping(value = "/userMS/buyer/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTOClass buyerDto){
		
		try {
		String myStr ="Buyer registered successfully with buyer Id : " + userService.buyerRegist(buyerDto);// B100
		return new ResponseEntity<>(myStr,HttpStatus.OK);
		}
		catch(UserExceptionClass e)
		{
			String myStr = environment.getProperty(e.getMessage());
			return new ResponseEntity<>(myStr,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	//to register the seller
	@RequestMapping(value = "/userMS/seller/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTOClass sellerDto){
		
		try {
		String myStr ="Seller registered successfully with seller Id : "+ userService.sellerRegist(sellerDto);
		return new ResponseEntity<>(myStr,HttpStatus.OK);
		}
		catch(UserExceptionClass e)
		{
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	//to login buyer using email and password
	@RequestMapping(value = "/userMS/buyer/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginBuyer(@RequestBody LoginDTOClass login)
	{
		try {
			String msg = userService.buyerLogin(login);
			
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(UserExceptionClass e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	//to login seller using email and password
	@RequestMapping(value = "/userMS/seller/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginSeller(@RequestBody LoginDTOClass login)
	{
		try {
			String msg = userService.sellerLogin(login);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(UserExceptionClass e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	//delete the  buyer (buyer id)
	@RequestMapping(value = "/userMS/buyer/deactivate/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBuyersAccount(@PathVariable String id){
		
		String msg = userService.deleteTheBuyer(id);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	//delete the seller(seller id)
	@RequestMapping(value = "/userMS/seller/deactivate/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteSellersAccount(@PathVariable String id){
		
		String msg = userService.deleteTheSeller(id);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	//to add product(product id) to wishlist of buyer(buyer id)
	@RequestMapping(value = "/userMS/buyer/wishlist/add/{buyerId}/{prodId}", method = RequestMethod.POST)
	public ResponseEntity<String> addProductToMyWishlist(@PathVariable String buyerId, @PathVariable String prodId) throws UserExceptionClass
	{
		try {
		
		ProductDTOClass product = new RestTemplate().getForObject(productUri+"/prodMS/getById/"+prodId, ProductDTOClass.class);
		
		String msg = userService.wishlistService(product.getProdId(), buyerId);
		
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			System.out.println(e);
			String newMsg = "There was some error";
			if(e.getMessage().equals("404 null"))
			{
				newMsg = "There are no PRODUCTS for the given product ID";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,newMsg,e);
		}
	}
	
	//add product(product id) & quantity(quantity) to buyer(buyer id) cart 
	@RequestMapping(value = "/userMS/buyer/cart/add/{buyerId}/{prodId}/{quantity}", method = RequestMethod.POST)
	public ResponseEntity<String> addProductToMyCart(@PathVariable String buyerId, @PathVariable String prodId, @PathVariable Integer quantity) throws UserExceptionClass
	{
		try {
		//System.out.println("hello");
		ProductDTOClass product = new RestTemplate().getForObject(productUri+"/prodMS/getById/"+prodId, ProductDTOClass.class);
		//System.out.println(product);
		//System.out.println(product instanceof ProductDTOClass);
		String msg = userService.cartService(product.getProdId(), buyerId, quantity);
		
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String newMsg = "There was some error";
			if(e.getMessage().equals("404 null"))
			{
				newMsg = "There are no PRODUCTS for the given product ID";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,newMsg,e);
		}
	}
	
	//get cart details of buyer(buyer id)
	@RequestMapping(value = "/userMS/buyer/cart/get/{buyerId}", method = RequestMethod.GET)
	public ResponseEntity<List<CartDTOClass>> getProductFromCart(@PathVariable String buyerId) throws UserExceptionClass
	{
		
		try {
		List<CartDTOClass> list = userService.getTheCartProducts(buyerId);
		
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
		}
		catch(UserExceptionClass e)
		{
			System.out.println(e.getMessage());
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
			
		}
	}
	
	//to remove product(product id) from buyer's(buyer id) cart
	@RequestMapping(value = "/userMS/buyer/cart/remove/{buyerId}/{prodId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeFromCart(@PathVariable String buyerId,@PathVariable String prodId) throws UserExceptionClass
	{
		
		try {
		String msg = userService.removeFromTheCart(buyerId, prodId);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(UserExceptionClass e)
		{
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
			
		}
	}
	
	//update reward ponts of buyer(buyer id)
	@RequestMapping(value = "/userMS/updateRewardPoints/{buyerId}/{rewPoints}", method = RequestMethod.POST)
	public ResponseEntity<String> updateRewardPoints(@PathVariable String buyerId, @PathVariable Integer rewPoints)
	{
		try {
			String msg = userService.updateTheRewardPoint(buyerId, rewPoints);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
	}
	
	@GetMapping(value = "/userMS/order/status/{orderId}/{status}")
	public ResponseEntity<String> UserOrderStatus(@PathVariable String orderId,
			@PathVariable String status) throws UserExceptionClass {
		try {
			String result = new RestTemplate().getForObject("http://localhost:8300/" + "order/status/" + orderId + "/" + status, String.class);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
	
	
}
}
