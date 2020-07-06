package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.Users;

@Controller
public class loginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	public String goToLogin(@ModelAttribute("user") Users user) {
//		System.out.println("Inside loginController GetMapping /login and /");
	    return "login";
	}
	
	@PostMapping("/login")
    public String loginUser(Model model, @Valid @ModelAttribute("user") Users user, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			return "login";
		} else {
			System.out.println(user.getUsername() +" "+ user.getPassword());
			
	        Users usr = userRepository.findByUsername(user.getUsername());
	        
	        System.out.println("after fetching the row from the DB");
			
			if (usr != null && user.getPassword().equalsIgnoreCase(usr.getPassword())) {
				
				model.addAttribute("currUserID", usr.getId());
				
				return "userHome";
			}
			else {
				model.addAttribute("userNotFound", "Given Username or Password is incorrect");
				return "login";
			}
		}
    }
}