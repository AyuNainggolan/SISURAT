package com.example.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.ApiSuratModel;
import com.example.model.JenisSuratModel;
import com.example.model.MahasiswaModel;
import com.example.model.PengajuanSuratModel;
import com.example.model.StatusSuratModel;
import com.example.service.JenisSuratService;
import com.example.service.PengajuanSuratService;
import com.example.service.StatusSuratService;

@RestController
@RequestMapping("/api")
public class SuratRestController {
	@Autowired
	PengajuanSuratService pengajuanSuratService;
	
	@Autowired
	StatusSuratService statusSuratService;
	
	@Autowired
	JenisSuratService jenisSuratService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/surat/view/{no_surat}")
	public HashMap<String, Object> view (@PathVariable(value="no_surat") String no_surat) {
		PengajuanSuratModel surat = pengajuanSuratService.getStatusSurat(no_surat);
		if(surat != null) {
			StatusSuratModel status = statusSuratService.selectStatusSurat(surat.getId_status_surat());
			JenisSuratModel jenis = jenisSuratService.selectJenisSurat(surat.getId_jenis_surat());
			MahasiswaModel mahasiswa = restTemplate.getForObject("https://apap-fasilkom.herokuapp.com/api/mahasiswa/view/id/3", MahasiswaModel.class);
			ApiSuratModel api_surat = new ApiSuratModel(mahasiswa.getId(), jenis.getNama(), surat.getKeterangan(), status.getNama());
		
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("Status", "200");
			result.put("Message", "Data ditemukan");
			result.put("Data Surat", api_surat);
			return result;
		}else {
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("Status", "404");
			result.put("Message", "Data tidak ditemukan");
			return result;
		}
	}
}
