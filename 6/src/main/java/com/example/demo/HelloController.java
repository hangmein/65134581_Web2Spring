package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	@RequestMapping("/hello") 
    public String sayHello(ModelMap model) {
        String loiChao ="Duc Nghia xin chao cac ban nha";
        model.addAttribute("message", loiChao);
        
        return "hello";
    }
	
	@RequestMapping(value="xu-ly-du-lieu", method=RequestMethod.POST)
	public String xuLyDuLieu(@RequestParam("hoTen") String tenNguoiDung, ModelMap model)
	{
		String loiChao="xin chào" + tenNguoiDung + ", Đức Nghĩa đã nhận được tin nhắn";
		model.addAttribute("thongBao", loiChao);
		
		return "LoiChaoBack";
	}
}
