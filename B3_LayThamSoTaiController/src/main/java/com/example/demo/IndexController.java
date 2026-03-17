package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	@RequestMapping("/login")
	public String hienThiForm() {
		return "login";
	}
	
	@RequestMapping(value = "/xu-ly-du-lieu", method = RequestMethod.POST)
	public String xuLyDangNhap(
			@RequestParam("tk") String id,
			@RequestParam("mk") String pw, ModelMap model) {
		if(id.equals("Nghia") && pw.equals("12345679"))
		{
			model.addAttribute("message", "đăng nhập thành công, chào mừng sếp quay lại");
			return "Done";
		} else {
            model.addAttribute("message", "Sai thông tin đăng nhập !"); 
            return "login";
        }
	}
}
