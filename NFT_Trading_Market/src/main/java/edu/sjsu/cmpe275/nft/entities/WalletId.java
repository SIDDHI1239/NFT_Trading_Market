package edu.sjsu.cmpe275.nft.entities;

import java.io.Serializable;
import java.util.Objects;

public class WalletId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int user;
	private String cryptocurrency;
	
	public WalletId() {
		
	}

	public WalletId(int user, String cryptocurrency) {
		super();
		this.user = user;
		this.cryptocurrency = cryptocurrency;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int userId) {
		this.user = userId;
	}

	public String getCryptocurrency() {
		return cryptocurrency;
	}

	public void setCryptocurrency(String cryptocurrency) {
		this.cryptocurrency = cryptocurrency;
	}

	// TO-DO equals() and hashCode()
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		WalletId walletId = (WalletId) o;

		return this.user == walletId.getUser() && this.cryptocurrency.equals(walletId.getCryptocurrency());
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.user, this.cryptocurrency);

	}
}