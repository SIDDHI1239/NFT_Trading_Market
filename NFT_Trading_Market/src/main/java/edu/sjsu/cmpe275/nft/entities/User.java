package edu.sjsu.cmpe275.nft.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "NICKNAME")
	private String nickName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PROVIDER")
	private String provider;

	@Column(name = "ENABLED")
	private boolean enabled;

	@Column(name = "IS_VERIFIED")
	private boolean isVerified;

	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<NFT> nfts;

	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Wallet> wallet;

	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Sale> sales;

	@JsonBackReference
	@OneToMany(mappedBy = "user")
//	@JoinTable(name = "user_salebid",
//	joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") },
//	inverseJoinColumns = {@JoinColumn(name = "BID_ID", referencedColumnName = "BID_ID") })
	private List<Bid> bids;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public List<NFT> getNfts() {
		return nfts;
	}

	public void setNfts(List<NFT> nfts) {
		this.nfts = nfts;
	}

	public List<Wallet> getWallet() {
		return wallet;
	}

	public void setWallet(List<Wallet> wallet) {
		this.wallet = wallet;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

}