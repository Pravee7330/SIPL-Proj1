package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nt.model.Vehicle;

@Repository
public interface IVehicleRepo extends JpaRepository<Vehicle,Integer> {

	@Query("select vm from Vehicle  vm where vm.registrationNumber=?1")
	Optional<Vehicle>    findByRegno(String registrationNumber);
}
