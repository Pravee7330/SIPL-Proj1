package com.sipl.yard.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sipl.yard.management.entities.RawCoordinatesEntity;

@Repository
public interface RawRepository extends JpaRepository<RawCoordinatesEntity, Integer> {
}
