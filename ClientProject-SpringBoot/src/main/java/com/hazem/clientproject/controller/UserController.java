package com.hazem.clientproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.hazem.clientproject.entity.User;
import com.hazem.clientproject.service.UserService;

@Controller
@RequestMapping("auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {
	@Autowired
    private UserService userService;

//	@RequestMapping(value="/user/signup", method = RequestMethod.GET)
//	public ModelAndView registration(){
//		ModelAndView modelAndView = new ModelAndView();
//		User user = new User();
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("signup");
//		return modelAndView;
//	}
//	
//	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
//	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
//		ModelAndView modelAndView = new ModelAndView();
//		User userExists = userService.findUserByName(user.getUsername());
//		if (userExists != null) {
//			bindingResult
//					.rejectValue("username", "error.user",
//							"There is already a user registered with the email provided");
//		}
//		if (bindingResult.hasErrors()) {
//			modelAndView.setViewName("signup");
//		} else {
//			userService.saveUser(user);
//			modelAndView.addObject("successMessage", "User has been registered successfully");
//			modelAndView.addObject("user", new User());
//			modelAndView.setViewName("signup");
//			
//		}
//		return modelAndView;
//	}
	@PostMapping("signup")
	public ResponseEntity<Void> createArticle(@RequestBody User user, UriComponentsBuilder builder) {
		boolean flag = userService.saveUser(user);
		if (flag == false) {
		     return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/user?id={id}").buildAndExpand(user.getIdUser()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
}
