package edu.sjsu.cmpe275.nft.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.nft.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("from User where email=:email")
	User findByEmail(@Param("email") String email);
}
