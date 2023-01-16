package com.sipl.yard.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sipl.yard.management.entities.UserMaster;

@Repository
public interface UserRespository extends CrudRepository<UserMaster, Integer>{

    @Query("select u from UserMaster u where u.username=?1 and u.active=true")
    Optional<UserMaster> findByActiveUserName(String userName);
    
    @Query("select u from UserMaster u where u.id=?1")
    UserMaster findByUserId(Integer userId);
}