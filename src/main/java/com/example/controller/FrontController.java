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
import com.example.model.PegawaiModel;
import com.example.model.PengajuanSuratModel;
import com.example.model.UserAccountModel;
import com.example.service.JenisSuratService;
import com.example.service.MahasiswaService;
import com.example.service.PegawaiService;
import com.example.service.PengajuanSuratService;
import com.example.service.StatusSuratService;
import com.example.service.PengajuanSuratService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FrontController {
	
    @Autowired
    MahasiswaService studentDAO;
    
    @Autowired
	PengajuanSuratService pengajuanSuratDAO;
    
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
    	String namaMahasiswa, namaPegawai;
    	log.info("Test");
    	List<PengajuanSuratModel> letter = pengajuanSuratDAO.selectPengajuan(username_pengaju);
    	for(int i=0;i<letter.size();i++) {
    		namaMahasiswa = searchName(letter.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(letter.get(i).getUsername_pegawai());
    		letter.get(i).setUsername_pengaju(namaMahasiswa);
    		letter.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("letter", letter);
    	return "riwayatSurat";
    }
    
    @RequestMapping("/pengajuan/viewall")
    public String viewPengajuanSurat(Model model) {
    	String namaMahasiswa, namaPegawai;
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectAllPengajuan();
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		System.out.println("nama pegawai "+namaPegawai);
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("lstSurat", lstSurat);
    	return "viewAllPengajuanSurat";
    }
    
    public String searchName(String npm) {
    	MahasiswaModel lstMahasiswa = studentDAO.selectMahasiswaByNPM(npm);
    	return lstMahasiswa.getNama();
    }
    
    public String searchNamaPegawai(String nip) {
    	PegawaiModel lstPegawai = pegawaiDAO.selectPegawaiByNIP(nip);
    	return lstPegawai.getNama();
    }
    
    @RequestMapping("/login")
	public String login() {
		return "index";
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
