package edu.sjsu.cmpe275.nft.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.nft.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {


	@Query("from Sale where seller_id=:userId")
	List<Sale> findAllSalesListedBy(@Param("userId") Long userId);
	
	@Query("FROM Sale WHERE seller_id != :userId AND closing_time IS NULL")
	List<Sale> findAllOpenedSales( @Param("userId") Long userId );
	
	@Query("select count(*) from sale where closing_time is null")
	int findActiveSales();
	
	@Query("select count(*) from sale where closing_time is null and type=1")
	int findActivePricedSales();
	
	@Query("select count(*) from sale where closing_time is null and type=0")
	int findActiveAuctionSales();
	
}
