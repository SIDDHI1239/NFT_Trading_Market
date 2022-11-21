package edu.sjsu.cmpe275.nft.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NFT")
public class NFT {

	@Id
	@Column(name = "NFT_ID")
	private String id;
	
	@Column(name = "SMART_CONTRACT_ADDRESS")
	private String smartContractAddress;
	
	@Column(name = "LAST_RECORDED_DATE")
	private Timestamp lastRecordedDate;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "IMAGE_URL")
	private String imageUrl;
	
	@Column(name = "ASSET_URL")
	private String assetUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSmartContractAddress() {
		return smartContractAddress;
	}

	public void setSmartContractAddress(String smartContractAddress) {
		this.smartContractAddress = smartContractAddress;
	}

	public Timestamp getLastRecordedDate() {
		return lastRecordedDate;
	}

	public void setLastRecordedDate(Timestamp lastRecordedDate) {
		this.lastRecordedDate = lastRecordedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAssetUrl() {
		return assetUrl;
	}

	public void setAssetUrl(String assetUrl) {
		this.assetUrl = assetUrl;
	}

}
