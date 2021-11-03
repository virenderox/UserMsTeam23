package com.FA.Team23.userMS.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.FA.Team23.userMS.utility.PrimaryKey;

@Entity
@Table(name = "wishlist")
public class WishlistClass {
	
	@EmbeddedId
	private PrimaryKey customId;

	public PrimaryKey getCustomId() {
		return customId;
	}

	public void setCustomId(PrimaryKey customId) {
		this.customId = customId;
	}

	@Override
	public String toString() {
		return "WishlistClass [customId=" + customId + "]";
	}
	
	

}
