package edu.sjsu.cmpe275.nft.entities;

import java.sql.Timestamp;

public class Sale {

	private int id;
	private char type;
	private String tokenId;
	private int sellerId;
	private String symbol;
	private float expectedValue;
	private float receivedValue;
	private Timestamp creationTime;
	private Timestamp closingTime;
	private Timestamp bidTime;
	private int buyerId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(float expectedValue) {
		this.expectedValue = expectedValue;
	}

	public float getReceivedValue() {
		return receivedValue;
	}

	public void setReceivedValue(float receivedValue) {
		this.receivedValue = receivedValue;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Timestamp closingTime) {
		this.closingTime = closingTime;
	}

	public Timestamp getBidTime() {
		return bidTime;
	}

	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

}
