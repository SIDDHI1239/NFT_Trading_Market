package edu.sjsu.cmpe275.nft.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sjsu.cmpe275.nft.entities.NFT;

public interface NFTRepository extends JpaRepository<NFT, String> {

}
