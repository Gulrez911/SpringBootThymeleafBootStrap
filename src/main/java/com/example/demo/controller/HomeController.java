package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;

@Controller
public class HomeController {

	@Autowired
	UserRepository repo;

	@GetMapping("/")
	public ModelAndView view() {
		ModelAndView mav = new ModelAndView("index");
		User user = new User();
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("save")
	public ModelAndView save(User user) {
		ModelAndView mav = new ModelAndView("index");
		System.out.println("Test: " + user);
		repo.save(user);
		mav.addObject("user", new User());
		return mav;
	}

	@Value("${welcome.message}")
	private String message;

	private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

	@GetMapping("/test")
	public String main(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("tasks", tasks);
		model.addAttribute("msg", "Welcome to Thymeleaf");
		return "welcome";
	}
}
