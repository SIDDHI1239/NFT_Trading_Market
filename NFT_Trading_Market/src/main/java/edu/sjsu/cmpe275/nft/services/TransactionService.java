package edu.sjsu.cmpe275.nft.services;

import java.sql.Date;
import java.util.List;

import edu.sjsu.cmpe275.nft.entities.Transaction;
import edu.sjsu.cmpe275.nft.entities.User;

public interface TransactionService {

	Transaction saveTransaction( Transaction transaction);
	
	List<Transaction> getTransactions(User user);
	
	List<Transaction> filterTransactions(User user, List<String> currencies, Date date);
	
	List<Transaction> filterSystemTransactions(List<String> currencies, Date date);

}
