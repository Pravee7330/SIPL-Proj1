package com.sipl.yard.management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.yard.management.entities.ContainerPosition;

@Repository
public interface ContainerPositionRepository extends JpaRepository<ContainerPosition, Long> {

	@Query("select cp from ContainerPosition cp where  cast (cp.auditEntity.createdAt as LocalDate) between ?1 and ?2 order by cp.id desc")
	List<ContainerPosition> getPickupDropData(LocalDate startDate, LocalDate endDate);
	
	@Query(value="SELECT * FROM container_placement_table a INNER JOIN  (SELECT container_number,Max(id) as i FROM container_placement_table where yard_id=?1 and drop_row!=0 GROUP BY container_number  ) AS b ON a.container_number = b.container_number  AND a.id = b.i",nativeQuery=true)
	List<ContainerPosition> getConatinerInfoByYardId(Integer yardID);
	
	@Query(value="SELECT * FROM container_placement_table a INNER JOIN  (SELECT container_number,Max(id) as i FROM container_placement_table where yard_id=?1 and container_number=?2  GROUP BY container_number  ) AS b ON a.container_number = b.container_number  AND a.id = b.i",nativeQuery=true)
	List<ContainerPosition> getConatinerInfoByYardIdAndcontainerNumber(Integer yardID , Optional<String> containerNumber);
	
	@Query(value="select * from container_placement_table where container_number=?1 Order by id desc LIMIT 1",nativeQuery=true)
	ContainerPosition findContainerPositionDetailsByContainer(String containerNumber);
	
	@Query(value="select cp from ContainerPosition cp where cast(cp.auditEntity.createdAt as LocalDate) between ?1 and ?2 ")
	List<ContainerPosition> findAllByDateGreaterThanEqualAndDateLessThanEqual(LocalDate startDate, LocalDate endDate);
}
