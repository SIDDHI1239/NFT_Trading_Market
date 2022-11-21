package edu.sjsu.cmpe275.nft.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SALE")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SALE_ID")
	private int id;
	
	@Column(name = "TYPE")
	private char type;
	
	@Column(name = "TOKEN_ID")
	private String tokenId;
	
	@Column(name = "SYMBOL")
	private String symbol;
	
	@Column(name = "EXPECTED_VALUE")
	private float expectedValue;
	
	@Column(name = "RECEIVED_VALUE")
	private float receivedValue;
	
	@Column(name = "CREATION_TIME")
	private Timestamp creationTime;
	
	@Column(name = "CLOSING_TIME")
	private Timestamp closingTime;
	
	@Column(name = "BID_TIME")
	private Timestamp bidTime;
	
	@Column(name = "BUYER_ID")
	private int buyerId;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "NFT_ID")
	private NFT nft;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "sale")
	private List<Cryptocurrency> currency;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public NFT getNft() {
		return nft;
	}

	public void setNft(NFT nft) {
		this.nft = nft;
	}

	public List<Cryptocurrency> getCurrency() {
		return currency;
	}

	public void setCurrency(List<Cryptocurrency> currency) {
		this.currency = currency;
	}

}
