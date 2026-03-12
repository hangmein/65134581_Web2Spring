package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hellospringboot {
	@RequestMapping("hellospring")
	@ResponseBody
	public String Hello()
	{
		return "B1_helloSpringBoot";
	}
	
	
}
