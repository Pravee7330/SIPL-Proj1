package com.sipl.yard.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.yard.management.entities.YardMaster;

@Repository
public interface YardMasterRepository extends JpaRepository<YardMaster, Integer>{
	
	@Query("select ym from YardMaster ym where ym.yard=?1")
	Optional<YardMaster> findByYardName(String yard);

}
