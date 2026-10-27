package com.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	// Le rôle ne peut jamais être posté depuis un formulaire (évite de se promouvoir admin)
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("role", "id");
	}
	
	// Home Page
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	// Show Register Form
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signUp";
	}
	
	// Registration Process
	@PostMapping("/register-success")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRole("USER");
		
		repo.save(user);
		
		return "registerSuccess";
	}
	
	// Show User List
	@GetMapping("/list")
	public String viewUserList(Model model) {
		List<User> users = repo.findAll();
		model.addAttribute("userList", users);
		
		return "userList";
	}
	
	// Show Update User Form
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {

		User user = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    model.addAttribute("selectedUser", user);
			    
		return "updateUser";
	}
	
	// Update User
	@PostMapping("/edit/{id}")
	public String updateUser(@PathVariable("id") int id, User user, 
	  BindingResult result, Model model) {
		User existing = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		// Le formulaire renvoie le hash déjà enregistré s'il n'est pas modifié : on ne le re-hache pas
		if (!user.getPassword().equals(existing.getPassword())) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}
		user.setId(id);
		user.setRole(existing.getRole());
		
	    if (result.hasErrors()) {
	        return "updateUser";
	    }
	        
	    repo.save(user);
	    return "redirect:/list";
	}
	
	// Delete User	
	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
	    User user = repo.findById(id)
	    		.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    // Le compte administrateur ne se supprime pas depuis l'interface
	    if (user.isAdmin()) {
	    	return "redirect:/list";
	    }
	    repo.delete(user);
	    
	    return "redirect:/list";
	}

}
