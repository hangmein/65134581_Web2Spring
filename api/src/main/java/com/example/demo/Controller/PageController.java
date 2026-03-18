package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.PageRepository;
import com.example.demo.Pages;
import com.example.demo.Service.PageService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PageController {
	
	@Autowired
    PageService pService;
	
    @GetMapping("/restAPI/page/all")
    public List<Pages> getAllPage() {
        List<Pages> dsTrang = new ArrayList<Pages>();
        dsTrang = pService.getAllPage();
        return dsTrang;
    }
    @GetMapping("/restAPI/page/{id}")
	public Pages getPage(@PathVariable("id") int id)
	{
    	return pService.getPageById(id);
	}
}