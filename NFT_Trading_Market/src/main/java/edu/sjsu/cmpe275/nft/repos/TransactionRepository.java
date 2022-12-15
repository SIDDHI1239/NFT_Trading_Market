package edu.sjsu.cmpe275.nft.repos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.nft.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("from Transaction where user_id=:userId")
	List<Transaction> findAllByUserId(@Param("userId") Long userId);
	
	@Query("FROM Transaction WHERE user_id=:userId AND symbol IN (:currencies) AND transaction_date >= :date ORDER BY transaction_date DESC")
	List<Transaction> filterTransactions(@Param("userId") Long userId, @Param("currencies") List<String> currencies, @Param("date") Date date);
	
	@Query("FROM Transaction WHERE symbol IN (:currencies) AND transaction_date >= :date")
	List<Transaction> filterSystemTransactions(@Param("currencies") List<String> currencies, @Param("date") Date date);
		
}
