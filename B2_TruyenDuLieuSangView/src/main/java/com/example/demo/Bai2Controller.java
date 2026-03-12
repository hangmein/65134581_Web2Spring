package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;


@Controller
public class Bai2Controller {	
	@RequestMapping("/Bai2")
	public String truyenThamSo(ModelMap model)
	{
		String loiChao = "Đuc Nghia xin chào các bạn nữ nha";
		model.addAttribute("message",loiChao);
		
		return "View";
	}
}
