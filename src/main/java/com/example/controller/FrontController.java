package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.MahasiswaModel;
import com.example.model.PengajuanSuratModel;
import com.example.model.UserAccountModel;
import com.example.service.JenisSuratService;
import com.example.service.MahasiswaService;
import com.example.service.PegawaiService;
import com.example.service.PengajuanSuratService;
import com.example.service.StatusSuratService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FrontController {
	
    @Autowired
    MahasiswaService studentDAO;
    
    @Autowired
    PengajuanSuratService pengajuanSuratDAO;
    
    @Autowired
    StatusSuratService statusSuratDAO;
    
    @Autowired
    PegawaiService pegawaiDAO;
    
    @Autowired
    JenisSuratService jenisSuratDAO;
    
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}

    @RequestMapping("/pengajuan/riwayat/{username_pengaju}")
    public String viewPengajuanSurat (Model model, @PathVariable(value = "username_pengaju") String username_pengaju)
    {
    	log.info("Test");
    	List<PengajuanSuratModel> letter = pengajuanSuratDAO.selectPengajuan(username_pengaju);
    	model.addAttribute("letter", letter);
    	return "riwayatSurat";
    }
    
    
	@RequestMapping("/listSurat")
	public String listSurat() {
		return "listSurat";
	}

}
