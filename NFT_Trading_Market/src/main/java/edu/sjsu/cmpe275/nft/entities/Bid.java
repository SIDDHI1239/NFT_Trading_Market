package edu.sjsu.cmpe275.nft.entities;

import java.sql.Timestamp;

public class Bid {

	private int id;
	private int saleId;
	private Timestamp bidTime;
	private int bidderId;
	private float bidValue;
	private Timestamp expirationTime;
	private Timestamp wasAccepted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public Timestamp getBidTime() {
		return bidTime;
	}

	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
	}

	public int getBidderId() {
		return bidderId;
	}

	public void setBidderId(int bidderId) {
		this.bidderId = bidderId;
	}

	public float getBidValue() {
		return bidValue;
	}

	public void setBidValue(float bidValue) {
		this.bidValue = bidValue;
	}

	public Timestamp getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Timestamp getWasAccepted() {
		return wasAccepted;
	}

	public void setWasAccepted(Timestamp wasAccepted) {
		this.wasAccepted = wasAccepted;
	}

}
