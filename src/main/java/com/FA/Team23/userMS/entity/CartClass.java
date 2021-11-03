package com.FA.Team23.userMS.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.FA.Team23.userMS.utility.PrimaryKey;

@Entity
@Table(name = "cart")
public class CartClass {
	
	@EmbeddedId
	private PrimaryKey customPK;
	
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public PrimaryKey getCustomPK() {
		return customPK;
	}

	public void setCustomPK(PrimaryKey customPK) {
		this.customPK = customPK;
	}

	@Override
	public String toString() {
		return "CartClass [customPK=" + customPK + ", quantity=" + quantity + "]";
	}
	
	

		
	
}

