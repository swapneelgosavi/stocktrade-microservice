package com.swapgo.stocktrade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swapgo.stocktrade.model.Share;

@Repository
public interface ShareRepository extends JpaRepository<Share, Integer>{
	Optional<Share> findByUserId(int id);
	
	/*
	 * @Query("delete from Share s WHERE LOWER(s.scriptName) = LOWER(:scriptName)")
	 * void deleteByScriptName(@Param("scriptName") String scriptName);
	 */
}
