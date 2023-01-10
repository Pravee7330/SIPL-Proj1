package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.UserMaster;

public interface IUserMasterRepo extends JpaRepository<UserMaster, Integer> {

	
	UserMaster getReferenceById(int id);

	UserMaster findByUsername(String username);
}
