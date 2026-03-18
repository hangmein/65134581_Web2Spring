package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BMIController {
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String TrangChu() {
		return "BMICalculator";
	}
	
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String tinhToanBMI(
			@RequestParam("chieuCao") double chieuCaoCm,
			@RequestParam("canNang") double canNangKg, 
			ModelMap model)
	{
		double chieuCaoM = chieuCaoCm/100;
		double bmi = canNangKg/(chieuCaoM * chieuCaoM);
		
		bmi = Math.round(bmi * 10.0) / 10.0;
		
		String danhGia = "";
        if (bmi < 18.5) {
            danhGia = "Bạn đang bị gầy (Thiếu cân).";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            danhGia = "Tuyệt vời! Cơ thể bạn rất bình thường và khỏe mạnh.";
        } else if (bmi >= 25 && bmi <= 29.9) {
            danhGia = "Mày béo.";
        } else {
            danhGia = "Mày đang bị béo";
        }
        
        model.addAttribute("message", danhGia);
		return "BMIResult";
	}
}

