package com.alpha.springboot.todomanagement.service;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import com.alpha.springboot.todomanagement.model.Users;

public interface IUsersService {

	List<Users> getUserByLogInUser(String user);

	Optional<Users> getUserById(long id);

	void updateUsers(Users todo);

	void addUsers(String loggedInUsername, String name,String address, Date targetDate, String zone);

	void deleteUsers(long id);
	
	void saveUsers(Users todo);

}