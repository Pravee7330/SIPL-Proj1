package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Role;

public interface IRoleRepo extends JpaRepository<Role, Integer> {

}
