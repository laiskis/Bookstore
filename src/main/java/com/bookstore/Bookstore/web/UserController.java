package com.bookstore.Bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookstore.Bookstore.domain.SignupForm;
import com.bookstore.Bookstore.domain.User;
import com.bookstore.Bookstore.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userepo;
	
	@RequestMapping(value = "/signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult br) {
		if(!br.hasErrors()) {
			if(signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				String pswd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPswd = bc.encode(pswd);
				
				User newUser = new User();
				newUser.setUsername(signupForm.getUsername());
				newUser.setPasswordHash(hashPswd);
				newUser.setEmail(signupForm.getEmail());
				newUser.setRole("USER");
				if (userepo.findByUsername(signupForm.getUsername()) == null) {
					userepo.save(newUser);
				}
				else {
					br.rejectValue("username", "err.username", "Username already exists!");
					return "signup";
					}
				}
				else {
					br.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match!");
					return "signup";
				}
			}
			else  {
				return "signup";
			}
			return "redirect:/login";
		
		}
	}

