package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;

@Controller
public class HelloController {
	@RequestMapping("/hello") 
    public String sayHello(ModelMap model) {
        String loiChao ="Duc Nghia xin chao cac ban nha";
        model.addAttribute("message", loiChao);
        
        return "hello";
    }
}
