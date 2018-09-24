package com.example.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.RoleDto;
import com.example.errors.ApiError;
import com.example.exceptions.BadRequestException;
import com.example.model.Permission;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.PermissionRepository;
import com.example.service.RoleService;
import com.example.service.UserService;

import java.util.ArrayList;
import java.util.List;

import javassist.tools.web.BadHttpRequest;

@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionRepository permissionRepository;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin-home");
		return modelAndView;
	}


	@RequestMapping(value = "/createrole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Role createRole(@RequestBody RoleDto roleDto) throws BadRequestException{
		if(roleDto.getName() == null || roleDto.getName().isEmpty())
		{
			throw new BadRequestException("Dtooo je njesraaa");
		}

		return roleService.saveRole(roleDto);
	}


	@RequestMapping(value = "/createrol", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Role> createRole2(@RequestBody RoleDto roleDto) throws BadRequestException{
		if(roleDto.getName() == null || roleDto.getName().isEmpty())
		{
			throw new BadRequestException("Name could not be null or empty");
		}
		Role role = roleService.saveRole(roleDto);

		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(value = "/viewroleform", method = RequestMethod.GET)
	public ModelAndView viewroleform()
	{
		ModelAndView modelAndView = new ModelAndView();
		//roleService.createRole(roleDto);
		List<Permission> permissions = permissionRepository.findAll();
		modelAndView.addObject("permissions", permissions);
		modelAndView.setViewName("create_role");

		return modelAndView;
	}


//	@ExceptionHandler(value = BadRequestException.class)
//	public ModelAndView handleBadRequestException(Exception e)
//	{
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("error");
//		return modelAndView;
//	}


//	@ExceptionHandler(value = BadRequestException.class)
//	public ResponseEntity<Object> handleBadRequestException2(BadRequestException ex)
//	{
//		BadRequestException e = ex;
//
//		return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
//	}


}
