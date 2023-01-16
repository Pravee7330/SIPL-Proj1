package com.sipl.yard.management.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.yard.management.entities.ContinuousCoordinatesEntity;

@Repository
public interface ContinuousRepository extends JpaRepository<ContinuousCoordinatesEntity, Integer> {

	@Query(value = "select * from continuous_coordinate_table cm where cm.vehicle_id=?1 order by cm.id desc LIMIT 1", nativeQuery = true)
	Optional<ContinuousCoordinatesEntity> findByVehicleId(String vehicleId);

	@Query(value = "SELECT MAX(cm.id) as id,(cm.vehicle_id),MAX(cm.latitude) as latitude, MAX(cm.longitude) as longitude,MAX(cm.altitude) as altitude FROM continuous_coordinate_table cm GROUP BY cm.vehicle_id", nativeQuery = true)
	List<Tuple> findContinuousCoordinatesByDistinctVehicleId();
}
