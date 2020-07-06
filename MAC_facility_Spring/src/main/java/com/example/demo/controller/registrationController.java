package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.Users;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class registrationController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/register")
	public String showRegistration(@ModelAttribute("user") Users user) {
		System.out.println("inside register GET call");
	    return "registration";
	}
	
	@PostMapping(path="/register")
    public String addNewUser (@Valid @ModelAttribute("user") Users user, BindingResult bindingResult, Model model) {
		System.out.println("inside register POST call to add new user");
		
		if (bindingResult.hasErrors()) {
			System.out.println("inside register form has errors");
			return "registration";
		} else {
			System.out.println("inside register form does NOT have errors");
			
			userRepository.save(user);

	        model.addAttribute("successMessage", "Your account has been successfully registered");
	        
	        return "registration";
		}
    }
	
	@GetMapping(path="/modifyProfile")
	public String modifyProfile(Users user, @RequestParam(value = "userID") Integer id, Model model) {
		
		System.out.println("Inside modifyProfile");
		System.out.println(id);
		
		Optional<Users> usr = userRepository.findById(id);
		
		if(usr.isPresent()) {
			System.out.println("inside user is present");
			
			user = usr.get();
//			model.addAttribute(user);
			
			model.addAttribute("currentUser", user);
			
			return "updateProfile";
			
		} else {
			System.out.println("inside user is NOT present");
			model.addAttribute("currentUser", new Users());
			
			return "updateProfile";
		}
	}
	
	@PostMapping(path="/UpdateProfile")
	public String updateProfile(@Valid @ModelAttribute("currentUser") Users user, BindingResult bindingResult, Model model) {
		System.out.println("inside UpdateProfile");
		
		if (bindingResult.hasErrors()) {
			System.out.println("inside UpdateProfile form has errors");
			
			return "updateProfile";
		} else {
			System.out.println("inside UpdateProfile form has NO error");
			System.out.println("ID - "+user.getId());
			
			Users userToUpdate = userRepository.getOne(user.getId());
			
			userToUpdate.setFirstname(user.getFirstname());
			userToUpdate.setLastname(user.getLastname());
			userToUpdate.setEmail(user.getEmail());
			userToUpdate.setRole(user.getRole());
			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setPassword(user.getPassword());
			
			userRepository.save(userToUpdate);
			
			model.addAttribute("successMessage", "Your Profile has been successfully updated");
			model.addAttribute("currentUser", new Users());
			
			return "updateProfile";
		}
	}
}
