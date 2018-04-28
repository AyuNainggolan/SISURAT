package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.UserAccountModel;
import com.example.service.MahasiswaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FrontController {
	
    @Autowired
    MahasiswaService studentDAO;
    
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
    @RequestMapping("/pengajuan/riwayat")
    public String view (Model model)
    {
    	log.info("Test");
        //List<UserAccountModel> students = studentDAO.selectAllStudents ();
        //model.addAttribute ("students", students);
        return "riwayatSurat";
    }
}
