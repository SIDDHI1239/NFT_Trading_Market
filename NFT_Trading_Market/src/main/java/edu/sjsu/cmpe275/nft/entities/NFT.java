package edu.sjsu.cmpe275.nft.entities;

import java.sql.Timestamp;

public class NFT {

	private String id;
	private String smartContractAddress;
	private Timestamp lastRecordedDate;
	private String name;
	private String type;
	private String description;
	private String imageUrl;
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
