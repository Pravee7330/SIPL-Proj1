package com.sipl.yard.management.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sipl.yard.management.entities.RoleMaster;

@Repository
public interface RoleRespository extends CrudRepository<RoleMaster, Integer> {

}
