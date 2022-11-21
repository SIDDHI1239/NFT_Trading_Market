package edu.sjsu.cmpe275.nft.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SALE_BID")
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BID_ID")
	private int id;
	
	@Column(name = "SALE_ID")
	private int saleId;
	
	@Column(name = "BID_TIME")
	private Timestamp bidTime;
	
	@Column(name = "BIDDER_ID")
	private int bidderId;
	
	@Column(name = "BID_VALUE")
	private float bidValue;
	
	@Column(name = "EXPIRATION_TIME")
	private Timestamp expirationTime;
	
	@Column(name = "WAS_ACCEPTED")
	private Timestamp wasAccepted;
	
	@JsonManagedReference
	@ManyToMany(mappedBy = "bids")
	private List<User> user;

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

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}