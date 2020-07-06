package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.LoginForm;
import com.example.demo.model.Users;

@Controller
public class loginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	public String showLogin(LoginForm loginForm) {
	    return "login";
	}
	
	@PostMapping("/login")
    public String loginUser(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult bindingResult, Model model){
		
		if (bindingResult.hasErrors()) {
			System.out.println("Inside errors found in Login form");
			
			for (Object object : bindingResult.getAllErrors()) {
			    if(object instanceof FieldError) {
			        FieldError fieldError = (FieldError) object;

			        System.out.println(fieldError.getCode());
			    }

			    if(object instanceof ObjectError) {
			        ObjectError objectError = (ObjectError) object;

			        System.out.println(objectError.getCode());
			    }
			}
			
			return "login";
			
		} else {
			System.out.println(loginForm.getUsername() +" "+ loginForm.getPassword());
			
	        Users usr = userRepository.findByUsername(loginForm.getUsername());
	        
	        System.out.println("after fetching the row from the DB");
			
			if (usr != null && loginForm.getPassword().equalsIgnoreCase(usr.getPassword())) {
				
				model.addAttribute("currUserID", usr.getId());
				
				return "userHome";
			}
			else {
				System.out.println("Inside user not found in DB");
				model.addAttribute("userNotFound", "Given Username or Password is incorrect");
				return "login";
			}
		}
    }
}