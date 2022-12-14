package edu.sjsu.cmpe275.nft.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.nft.entities.Transaction;
import edu.sjsu.cmpe275.nft.entities.User;
import edu.sjsu.cmpe275.nft.repos.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	@Transactional
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	@Override
	@Transactional
	public List<Transaction> getTransactions(User user) {
		Long userId = user.getId();
		return transactionRepository.findAllByUserId(userId);
	}
	
//	@Override
//	@Transactional
//	public List<Transaction> filterTransactions(User user, String currency, int days) {
//		Long userId = user.getId();
//		return transactionRepository.filterTransactions(userId, currency, days);
//	}
	
	@Override
	@Transactional
	public List<Transaction> filterLastOneDayTransactions(User user, String currency) {
		Long userId = user.getId();
		return transactionRepository.filterLastOneDayTransactions(userId, currency);
	}
	
	@Override
	@Transactional
	public List<Transaction> filterLastOneWeekTransactions(User user, String currency) {
		Long userId = user.getId();
		return transactionRepository.filterLastOneWeekTransactions(userId, currency);
	}
	
	@Override
	@Transactional
	public List<Transaction> filterLastOneMonthTransactions(User user, String currency) {
		Long userId = user.getId();
		return transactionRepository.filterLastOneMonthTransactions(userId, currency);
	}
}
