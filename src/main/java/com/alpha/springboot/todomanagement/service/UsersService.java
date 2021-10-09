package com.alpha.springboot.todomanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.springboot.todomanagement.model.Users;
import com.alpha.springboot.todomanagement.repository.UsersRepository;

@Service
public class UsersService implements IUsersService {

	@Autowired
	private UsersRepository todoRepository;

	@Override
	public List<Users> getUserByLogInUser(String user) {
		return todoRepository.findByUserName(user);
	}

	@Override
	public Optional<Users> getUserById(long id) {
		return todoRepository.findById(id);
	}

	@Override
	public void updateUsers(Users todo) {
		todoRepository.save(todo);
	}
	
	@Override
	public void addUsers(String loggedInUsername, String name,String address, Date dateOfBirth, String zone) {
		todoRepository.save(new Users(loggedInUsername,name,dateOfBirth, address,zone));
		
	}

	@Override
	public void deleteUsers(long id) {
		Optional<Users> todo = todoRepository.findById(id);
		if (todo.isPresent()) {
			todoRepository.delete(todo.get());
		}
	}

	@Override
	public void saveUsers(Users todo) {
		todoRepository.save(todo);
	}
}