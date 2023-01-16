package com.sipl.yard.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sipl.yard.management.entities.ContainerMaster;

public interface ContainerMasterRepository extends CrudRepository<ContainerMaster, Integer> {

	@Query("select cm from ContainerMaster cm where cm.size=?1")
	Optional<ContainerMaster> findBySize(Integer size);

}
