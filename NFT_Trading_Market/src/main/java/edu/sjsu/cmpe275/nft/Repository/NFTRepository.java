package edu.sjsu.cmpe275.nft.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.nft.entities.NFT;

@Repository
public interface NFTRepository extends JpaRepository<NFT, String> {

}
