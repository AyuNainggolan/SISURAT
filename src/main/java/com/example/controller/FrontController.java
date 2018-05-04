package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.model.UserAccountModel;
import com.example.service.MahasiswaService;
import com.example.service.PengajuanSuratService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FrontController {
	
    @Autowired
    MahasiswaService studentDAO;
    @Autowired
	PengajuanSuratService pengajuanSuratDAO;
    
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
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

	@RequestMapping("/listSurat")
	public String listSurat() {
		return "listSurat";
	}
	
	@RequestMapping("/pengajuan/view/{id_pengajuan_surat}")
	public String detailPengajuanSurat(Model model, @PathVariable(value = "id_pengajuan_surat") int id_pengajuan_surat) {
		model.addAttribute("surat", pengajuanSuratDAO.getDetailPengajuanSurat(id_pengajuan_surat));
		
		return "detailPengajuanSurat";
	}

}
