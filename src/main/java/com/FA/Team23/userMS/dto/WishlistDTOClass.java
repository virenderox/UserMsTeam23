package com.FA.Team23.userMS.dto;

public class WishlistDTOClass {
	
	private String buyerId;
	private String prodId;
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	@Override
	public String toString() {
		return "WishlistDTOClass [buyerId=" + buyerId + ", prodId=" + prodId + "]";
	}

	
}

