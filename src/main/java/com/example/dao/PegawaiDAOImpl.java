package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.PegawaiModel;

@Service
public class PegawaiDAOImpl implements PegawaiDAO {
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public PegawaiModel selectPegawaiByNIP(String nip) {
		PegawaiModel pegawai = restTemplate.getForObject("https://apap-fasilkom.herokuapp.com/api/staf/view/nip/"+nip, PegawaiModel.class);
		return pegawai;
	}
}
