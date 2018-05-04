package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.dao.PegawaiMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PegawaiServiceDatabase implements PegawaiService {
	@Autowired
	private PegawaiMapper pegawaiMapper;
	
	@Override
	public String getNamaPegawai(String username) {
		log.info ("select pegawai with username {}", username);
		return pegawaiMapper.getNamaPegawai(username);
	}
	
}
