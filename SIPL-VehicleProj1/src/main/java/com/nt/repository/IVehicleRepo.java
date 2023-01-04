package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nt.model.Vehicle;

@Repository
public interface IVehicleRepo extends CrudRepository<Vehicle,Integer> {

	@Query("select vm from Vehicle  vm where vm.registrationNumber=?1")
	Optional<Vehicle>    findByRegno(String registrationNumber);
	
	@Query("select v from Vehicle v where v.id=?1")
	  Vehicle getReferenceById(int id);
	
	//Optional<Vehicle> findByReg_num(String reg_num);
}
