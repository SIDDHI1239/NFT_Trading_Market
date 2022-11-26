package edu.sjsu.cmpe275.nft.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.nft.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("from User where email=:email")
	User findByEmail(@Param("email") String email);
	
	@Query("from User where nickname=:nickname")
	User findByNickName(@Param("nickname") String nickName);
	
	@Query("from User where token=:token")
	User findByToken(@Param("token") String token);
}
