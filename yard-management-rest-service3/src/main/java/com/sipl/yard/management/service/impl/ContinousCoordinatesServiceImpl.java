package com.sipl.yard.management.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.yard.management.entities.ContinuousCoordinatesEntity;
import com.sipl.yard.management.mapper.ContinuousCoordinatesMapper;
import com.sipl.yard.management.repository.ContinuousRepository;
import com.sipl.yard.management.responses.ContinuousCoordinatesApiResponse;
import com.sipl.yard.management.service.ContinousCoordinatesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContinousCoordinatesServiceImpl implements ContinousCoordinatesService {

	@Autowired
	private ContinuousRepository continuousRepository;

	@Autowired
	private ContinuousCoordinatesMapper continuousCoordinatesMapper;

	@Override
	public ContinuousCoordinatesApiResponse getContinuousDataByVehicleId(String vehicleId) {
		try {
			log.info("<<START>> getContinuousDataByVehicleId <<START>>");
			log.info("Fetching continuous coordinates by VehicleId");
			final Optional<ContinuousCoordinatesEntity> continuousCoordinateEntities = continuousRepository
					.findByVehicleId(vehicleId);
			if (!continuousCoordinateEntities.isPresent()) {
				log.info("Continuous coordinates not found against vehicleId : " + vehicleId);
				log.info("<<END>> getContinuousDataByVehicleId <<END>>");
				return new ContinuousCoordinatesApiResponse(null, null, HttpStatus.NOT_FOUND,
						"Continuous coordinates list not found", true);
			}
			log.info("continuous coordinates found!");
			ContinuousCoordinatesEntity continuousCoordinatesEntity = continuousCoordinateEntities.get();
			log.info("<<END>> getContinuousDataByVehicleId <<END>>");
			return new ContinuousCoordinatesApiResponse(
					continuousCoordinatesMapper.mapContinuousCoordinatesEntityToContinuousCoordinatesEntityDto(
							continuousCoordinatesEntity),
					null, HttpStatus.FOUND, "Continuous coordinates list found", false);

		} catch (Exception e) {
			log.error("Exception occurred in getContinuousDataByVehicleId(): ", e);
		}
		log.info("<<END>> getContinuousDataByVehicleId <<END>>");
		return new ContinuousCoordinatesApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Exception",
				true);
	}
	
	
	public ContinuousCoordinatesApiResponse getContinuousCoordinatesByDistinctVehicleId() {
		try {
			log.info("<<START>> getContinuousCoordinatesByDistinctVehicleId <<START>>");
			log.info("Fetching continuous coordinates ");
			final List<Tuple> continuousCoordinateEntitiesOfList = continuousRepository
					.findContinuousCoordinatesByDistinctVehicleId();
			if (continuousCoordinateEntitiesOfList.isEmpty()) {
				log.info("Continuous coordinates not found");
				log.info("<<END>> getContinuousDataByVehicleId <<END>>");
				return new ContinuousCoordinatesApiResponse(null, null, HttpStatus.NOT_FOUND,
						"Continuous coordinates list not found", true);
			}
			List<ContinuousCoordinatesEntity> continuousCoordinatesEntityList = new ArrayList<>();
			for (Tuple continuousCoordinateTuple : continuousCoordinateEntitiesOfList) {
				ContinuousCoordinatesEntity continuousCoordinatesEntity = new ContinuousCoordinatesEntity();
				continuousCoordinatesEntity.setId(((BigInteger)continuousCoordinateTuple.get(0)).longValue());
				continuousCoordinatesEntity.setVehicleId((String) continuousCoordinateTuple.get(1));
				continuousCoordinatesEntity.setLatitude((Double) continuousCoordinateTuple.get(2));
				continuousCoordinatesEntity.setLongitude((Double) continuousCoordinateTuple.get(3));
				continuousCoordinatesEntity.setAltitude(((Double)continuousCoordinateTuple.get(4)).floatValue());
				continuousCoordinatesEntityList.add(continuousCoordinatesEntity);
			}
			log.info("continuous coordinates found!");
			log.info("<<END>> getContinuousCoordinatesByDistinctVehicleId <<END>>");
			return new ContinuousCoordinatesApiResponse(null,
					continuousCoordinatesMapper.continuousCoordinatesEntityListToContinuousCoordinatesEntityDtoList(
							continuousCoordinatesEntityList),
					HttpStatus.FOUND, "Continuous coordinates list found", false);

		} catch (Exception e) {
			log.error("Exception occurred in getContinuousCoordinatesByDistinctVehicleId(): ", e);
		}
		log.info("<<END>> getContinuousCoordinatesByDistinctVehicleId <<END>>");
		return new ContinuousCoordinatesApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Exception",
				true);
	}
}
