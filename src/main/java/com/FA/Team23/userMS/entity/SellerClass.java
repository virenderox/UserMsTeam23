package com.FA.Team23.userMS.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seller")
public class SellerClass {
	
	@Id
	private String sellerId;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private String isActive;
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Override
	public int hashCode() {
		return Objects.hash(sellerId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellerClass other = (SellerClass) obj;
		return Objects.equals(sellerId, other.sellerId);
	}
	@Override
	public String toString() {
		return "SellerClass [sellerId=" + sellerId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", password=" + password + ", isActive=" + isActive + "]";
	}
	
	

}
