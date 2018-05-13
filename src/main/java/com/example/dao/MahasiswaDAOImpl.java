package com.example.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.model.MahasiswaModel;

import org.springframework.web.client.RestTemplate;

@Service
public class MahasiswaDAOImpl implements MahasiswaDAO {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<MahasiswaModel> selectAllMahasiswa() {
		MahasiswaModel[] mahasiswa = restTemplate.getForObject("https://apap-fasilkom.herokuapp.com/api/mahasiswa/viewall/", MahasiswaModel[].class);
		List<MahasiswaModel> list_mahasiswa = Arrays.asList(mahasiswa);
		return list_mahasiswa;
	}
	
	@Override
	public MahasiswaModel selectMahasiswaByNPM(String npm) {
		MahasiswaModel mahasiswa = restTemplate.getForObject("https://apap-fasilkom.herokuapp.com/api/mahasiswa/view/npm/"+npm, MahasiswaModel.class);
		return mahasiswa;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	public Boolean selectMahasiswaAsdosByNPM(int id) {
		Boolean isAsdos = restTemplate.getForObject("http://kelompok-a.herokuapp.com/asisten-dosen/cek-status?id="+id, Boolean.class);
		return isAsdos;
	}
}
