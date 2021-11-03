package com.FA.Team23.userMS.validator;

import com.FA.Team23.userMS.dto.BuyerDTOClass;
import com.FA.Team23.userMS.dto.SellerDTOClass;
import com.FA.Team23.userMS.exception.UserExceptionClass;

public class ValidateClass {
	
	//validate the buyer
	public static void validateTheBuyer(BuyerDTOClass buyer) throws UserExceptionClass {
		
		if(!validateTheName(buyer.getName()))  
			throw new UserExceptionClass("Validator.ENTER_VALID_NAME");
		
		if(!validateTheEmail(buyer.getEmail()))
			throw new UserExceptionClass("Validator.ENTER_VALID_EMAIL");
		
		if(!validateTheContactNumber(buyer.getPhoneNumber()))
			throw new UserExceptionClass("Validator.ENTER_VALID_NUMBER");
		
			
		if(!validateThePassword(buyer.getPassword()))
			throw new UserExceptionClass("Validator.Enter_VALID_PASSWORD");
		
	}
	
	//validate the seller
	public static void validateTheSeller(SellerDTOClass buyer) throws UserExceptionClass {
		
		if(!validateTheName(buyer.getName()))
			throw new UserExceptionClass("Validator.ENTER_VALID_NAME");
		
		if(!validateTheEmail(buyer.getEmail()))
			throw new UserExceptionClass("Validator.ENTER_VALID_EMAIL");
		
		if(!validateTheContactNumber(buyer.getPhoneNumber()))
			throw new UserExceptionClass("Validator.ENTER_VALID_NUMBER");
		
			
		if(!validateThePassword(buyer.getPassword()))
			throw new UserExceptionClass("Validator.Enter_VALID_PASSWORD");
		
	}
	
	//Validate the Name
	public static boolean validateTheName(String name)
	{
		
		String rgx = "[A-Za-z]+([ ][A-Za-z]+)*";
		
		if(name.matches(rgx))
			return true;
		
		return false;
		
	}
	
	//validate the email
	public static boolean validateTheEmail(String email)
	{
		String reg = "[A-za-z]+@[A-za-z]+[\\.]com";
		
		if(email.matches(reg))
			return true;
		
		return false;
	}

	//validate the contact Number
	public static boolean validateTheContactNumber(String contactNumber)
	{
		
		String reg = "[6,7,8,9][0-9]{9}";
		
		if(contactNumber.matches(reg))
			return true;
		
		return false;
	}
	
	//validate the password
	public static boolean validateThePassword(String password)
	{
		String rgx = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{7,20}$";
		
		if(password.matches(rgx))
			return true;
		
		return false;
	}	

}
