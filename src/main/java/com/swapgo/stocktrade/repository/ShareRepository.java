package com.swapgo.stocktrade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapgo.stocktrade.model.Share;

@Repository
public interface ShareRepository extends JpaRepository<Share, Integer>{
	Optional<Share> findByUserId(int id);
	void deleteByScriptName(String scriptName);

}
