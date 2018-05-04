package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.JenisSuratMapper;
import com.example.dao.MahasiswaMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JenisSuratServiceDatabase implements JenisSuratService {
	@Autowired
	private JenisSuratMapper jenisSuratMapper;
	
	@Override
	public String getNamaJenisSurat(int id_surat) {
		log.info ("select student with id_surat {}", id_surat);
		return jenisSuratMapper.getNamaJenisSurat(id_surat);
	}

}
