package com.alpha.springboot.todomanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.springboot.todomanagement.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	List<Users> findByUserName(String user);
}
