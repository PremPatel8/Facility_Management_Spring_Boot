package com.example.demo.controller;

import java.util.Optional;

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

@Controller
public class registrationController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/register")
	public String goToRegistration() {
		System.out.println("Inside registrationController GetMapping /register");
	    return "registration";
	}
	
	@PostMapping(path="/register")
    public String addNewUser (Model model, Users user) {
		
//		System.out.println(user.getFirstname() +" "+ user.getLastname() +" "+ user.getEmail() +" "+ user.getRole() +" "+ user.getUsername() +" "+ user.getPassword());

        userRepository.save(user);

        model.addAttribute("successMessage", "Your account has been successfully registered");
        return "registration";
    }
	
	@GetMapping(path="/modifyProfile")
	public String modifyProfile( @RequestParam(value = "userID") Integer id, Model model) {
		
		System.out.println("Inside modifyProfile");
		System.out.println(id);
		
		Optional<Users> user = userRepository.findById(id);
				
		model.addAttribute("currentUser", user);
		
		return "updateProfile";
	}
	
	@PostMapping(path="/UpdateProfile")
	public String updateProfile(Model model, Users user) {
		System.out.println(user.getFirstname() +" "+ user.getLastname() +" "+ user.getEmail() +" "+ user.getRole() +" "+ user.getUsername() +" "+ user.getPassword());
		
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
