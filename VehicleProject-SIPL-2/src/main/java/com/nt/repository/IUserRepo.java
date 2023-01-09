package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.User;

public interface IUserRepo extends JpaRepository<User, Integer> {

}
