package com.alpha.springboot.todomanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alpha.springboot.todomanagement.model.Users;
import com.alpha.springboot.todomanagement.service.IUsersService;

@Controller
public class UsersController {

	@Autowired
	private IUsersService todoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-users", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("listUsers", todoService.getUserByLogInUser(name));
		// model.put("todos", service.retrieveTodos(name));
		return "list-users";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@RequestMapping(value = "/add-user", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("addUser", new Users());
		return "addUser";
	}

	@RequestMapping(value = "/delete-user", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam long id) {
		todoService.deleteUsers(id);
		// service.deleteTodo(id);
		return "redirect:/list-users";
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
		Users todo = todoService.getUserById(id).get();
		model.put("addUser", todo);
		return "addUser";
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Users todo, BindingResult result) {

		if (result.hasErrors()) {
			return "addUser";
		}

		todo.setUserName(getLoggedInUserName(model));
		todoService.updateUsers(todo);
		return "redirect:/list-users";
	}

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Users addUser, BindingResult result) {

		System.out.println("Add User is submitted addUser:"+addUser);
		System.out.println("Add User is submitted result:"+result.toString());
		if (result.hasErrors()) {
			return "addUser";
		}

		addUser.setUserName(getLoggedInUserName(model));
		todoService.saveUsers(addUser);
		return "redirect:/list-users";
	}
}
