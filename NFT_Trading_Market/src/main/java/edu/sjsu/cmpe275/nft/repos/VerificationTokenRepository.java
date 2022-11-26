package edu.sjsu.cmpe275.nft.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.nft.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	@Query("from verification_token where token=:token")
	VerificationToken findByToken(@Param("token") String token);
}
