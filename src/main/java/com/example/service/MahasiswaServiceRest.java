package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.dao.MahasiswaDAO;
import com.example.model.MahasiswaModel;

import groovy.util.logging.Slf4j;


@Slf4j
@Service
@Primary
public class MahasiswaServiceRest implements MahasiswaService {
	@Autowired
	private MahasiswaDAO mahasiswaDAO;
	
	@Override
	public List<MahasiswaModel> selectAllMahasiswa(){
		//log.info ("REST - select all students");
		return mahasiswaDAO.selectAllMahasiswa();
	}
	
	@Override
	public MahasiswaModel selectMahasiswaByNPM(String npm) {
		//log.info ("REST - select all students");
		return mahasiswaDAO.selectMahasiswaByNPM(npm);
	}
}
