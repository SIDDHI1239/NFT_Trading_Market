package edu.sjsu.cmpe275.nft.services;

import java.util.List;

import edu.sjsu.cmpe275.nft.entities.Transaction;
import edu.sjsu.cmpe275.nft.entities.User;

public interface TransactionService {

	List<Transaction> getTransactions(User user);
	
//	List<Transaction> filterTransactions(User user, String currency, int days);
	
	List<Transaction> filterLastOneDayTransactions(User user, String currency);
	
	List<Transaction> filterLastOneWeekTransactions(User user, String currency);
	
	List<Transaction> filterLastOneMonthTransactions(User user, String currency);
}
