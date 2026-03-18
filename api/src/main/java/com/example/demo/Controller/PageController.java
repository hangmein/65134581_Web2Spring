package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.PageRepository;
import com.example.demo.Pages;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PageController {

	@GetMapping("restAPI/page/all")
	public ArrayList<Pages> getAllPage()
	{
		ArrayList<Pages> dsTrang = new ArrayList<Pages>();
		dsTrang.add(new Pages(0, "Trang chủ", "từ khóa", "nội dung", 0));
		return dsTrang;
	}
}