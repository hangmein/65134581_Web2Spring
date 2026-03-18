package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.PageRepository;
import com.example.demo.Pages;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PageController {
	
	@Autowired
    private PageRepository pageRepository;
    @GetMapping("/all")
    public List<Pages> getAllPage() {
        return pageRepository.findAll();
    }
}