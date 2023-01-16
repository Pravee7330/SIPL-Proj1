package com.sipl.yard.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.yard.management.dtos.VehicleMasterDto;
import com.sipl.yard.management.entities.VehicleMaster;
import com.sipl.yard.management.mapper.VehicleMasterMapper;
import com.sipl.yard.management.repository.VehicleMasterRepository;
import com.sipl.yard.management.responses.VehicleMasterApiResponse;
import com.sipl.yard.management.service.VehicleMasterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VehicleMasterServiceImpl implements VehicleMasterService {
	
	@Autowired
	private VehicleMasterRepository vehicleMasterRepository;

	@Autowired
	private VehicleMasterMapper vehicleMasterMapper;
	
	@CacheEvict(value = "getAllVehicleMaster", allEntries = true)
	@Override
	public VehicleMasterApiResponse add(VehicleMasterDto vehicleMasterDto) {
		log.info("<<start>>In add method<<start>>");
		try {
			if (vehicleMasterRepository.findByVehicleId(vehicleMasterDto.getVehicleId()).isPresent()) {
				return new VehicleMasterApiResponse(null, null, HttpStatus.ALREADY_REPORTED,
						vehicleMasterDto.getVehicleId() + " already present", true);
			} else {
				VehicleMaster dataFromdb = vehicleMasterRepository
						.save(vehicleMasterMapper.mapVehicleMasterDtoToVehicleMaster(vehicleMasterDto));
				return new VehicleMasterApiResponse(vehicleMasterMapper.mapVehicleMasterToVehicleMasterDto(dataFromdb), null,
						HttpStatus.CREATED, "Vehicle added successfully", false);
			}
		} catch (Exception e) {
			log.error("Exception in add method of VehicleMaster",e);
		}
		return new VehicleMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
	
	@CacheEvict(value = "getAllVehicleMaster", allEntries = true)
	@Override
	public VehicleMasterApiResponse update(VehicleMasterDto vehicleMasterDto) {
		log.info("<<start>>In update method<<start>>");
		try {
			Optional<VehicleMaster> vehicleMasterFatchFromDb = vehicleMasterRepository
					.findById(vehicleMasterDto.getId());
			if (vehicleMasterFatchFromDb.isPresent()) {
				VehicleMaster vehicleDetailsFromDb = vehicleMasterFatchFromDb.get();
				vehicleDetailsFromDb.setVehicleId(vehicleMasterDto.getVehicleId());
				vehicleDetailsFromDb.setType(vehicleMasterDto.getType());
				VehicleMaster updatedVehicle = vehicleMasterRepository.save(vehicleDetailsFromDb);
				log.info("<<end>>update<<end>>");
				return new VehicleMasterApiResponse(
						vehicleMasterMapper.mapVehicleMasterToVehicleMasterDto(updatedVehicle), null, HttpStatus.OK,
						"Vehicle Details Updated successfully", false);

			} else {
				return new VehicleMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Vehicle Id Does Not Exists", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Yard updateYard JDBCConnectionException: ", e);
			return new VehicleMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}

	}
	
	@CacheEvict(value = "getAllVehicleMaster", allEntries = true)
	@Override
	public VehicleMasterApiResponse deleteById(VehicleMasterDto vehicleMasterDto) {
		log.info("<<start>>In deleteById method<<start>>");
		try {
			int id = vehicleMasterDto.getId();
			log.debug("In deleteById method Id: " +id);
			final Optional<VehicleMaster> vehicleFetchedFromDb = vehicleMasterRepository.findById(id);
			log.debug("Vehicle Details Fetched From Db " + vehicleFetchedFromDb);
			if (vehicleFetchedFromDb.isPresent()) {
				VehicleMaster vehicleMaster = vehicleFetchedFromDb.get();
				vehicleMasterRepository.deleteById(id);
				return new VehicleMasterApiResponse(
						vehicleMasterMapper.mapVehicleMasterToVehicleMasterDto(vehicleMaster), null, HttpStatus.OK,
						"Vehicle " + vehicleMaster.getVehicleId() + " deleted successfully", false);
			} else {
				log.info("<<end>>In deleteById method<<end>>");
				return new VehicleMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Vehicle id doesn't exists", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Vehicle deleteById JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("Vehicle deleteById Exception: ", e);
		}
		return new VehicleMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Cacheable("getAllVehicleMaster")
	@Override
	public VehicleMasterApiResponse getAll() {
		log.info("<<start>>In getAll method<<start>>");
		try {
			List<VehicleMaster> vehicleMasters = (List<VehicleMaster>) vehicleMasterRepository.findAll();
			if (!vehicleMasters.isEmpty()) {
				return new VehicleMasterApiResponse(null, vehicleMasterMapper.vehicleMasterListToVehicleMasterDtoList(vehicleMasters),
						HttpStatus.FOUND, "Vehicle master found successfully", false);
			} else {
				return new VehicleMasterApiResponse(null, null, HttpStatus.NOT_FOUND, "Records not found", true);
			}
		} catch (Exception e) {
			log.error("Exception in getAll ", e);
		}
		return new VehicleMasterApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
	}

}
