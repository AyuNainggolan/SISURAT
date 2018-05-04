package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MahasiswaMapper;
import com.example.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService{
	
	@Autowired
	private MahasiswaMapper mahasiswaMapper;

//	@Override
//	public String getNamaMhs(String username) {
//		log.info ("select student with username {}", username);
//		return mahasiswaMapper.getNamaMhs(username);
//	}
//
//	@Override
//	public String getNPMMhs(String username) {
//		log.info ("select student with username {}", username);
//		return mahasiswaMapper.getNPMMhs(username);
//	}

}
