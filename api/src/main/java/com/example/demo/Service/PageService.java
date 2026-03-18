package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.PageRepository;

import com.example.demo.PageRepository;
import com.example.demo.Pages;

@Service
public class PageService {
	@Autowired
	PageRepository pRepos;
	public List<Pages> getAllPage()
	{
		return pRepos.findAll();
	}
}
