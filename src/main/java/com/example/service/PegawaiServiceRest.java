package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.dao.MahasiswaDAO;
import com.example.dao.PegawaiDAO;
import com.example.model.MahasiswaModel;
import com.example.model.PegawaiModel;

import groovy.util.logging.Slf4j;

@Slf4j
@Service
@Primary
public class PegawaiServiceRest implements PegawaiService {
	@Autowired
	private PegawaiDAO pegawaiDAO;
	
	@Override
	public PegawaiModel selectPegawaiByNIP(String nip) {
		//log.info ("REST - select all students");
		return pegawaiDAO.selectPegawaiByNIP(nip);
	}
}
