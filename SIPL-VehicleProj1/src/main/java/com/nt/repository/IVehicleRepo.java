package com.nt.repository;

import org.springframework.data.repository.CrudRepository;

import com.nt.model.Vehicle;

public interface IVehicleRepo extends CrudRepository<Vehicle, Integer> {


}
