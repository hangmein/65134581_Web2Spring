package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controllers {

	@GetMapping("/")
	public String index()
	{
		return "homepage";
	}
	
	@GetMapping("/products")
	public String index2()
	{
		return "sanpham";
	}
	
	@GetMapping("/admin")
	public String index3()
	{
		return "admin";
	}
	
	@GetMapping("/hello")
	public String hi()
	{
		return "hello";
	}
	
	@GetMapping("/login")
	public String openLoginForm()
	{
		return "login";
	}
	
}