package com.sipl.yard.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sipl.yard.management.entities.VehicleMaster;

@Repository
public interface VehicleMasterRepository extends CrudRepository<VehicleMaster, Integer> {
	
	@Query("select vm from VehicleMaster vm where vm.vehicleId=?1")
	Optional<VehicleMaster> findByVehicleId(String vehicleId);
}
