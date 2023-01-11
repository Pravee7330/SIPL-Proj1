package com.nt.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.nt.model.Vehicle;

@Repository
public interface IVehicleRepo extends PagingAndSortingRepository<Vehicle,Integer> {

	@Query("select vm from Vehicle  vm where vm.registrationNumber=?1")
	Optional<Vehicle>    findByRegno(String registrationNumber);
	

	Vehicle getReferenceById(int id);

	//Page<Vehicle> findAll(Pageable pageable);
	
	List<Vehicle> findAllByDateGreaterThanEqualAndDateLessThanEqual(LocalDate startDate, LocalDate endDate);
}
