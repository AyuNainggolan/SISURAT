package com.example.service;

import java.util.List;

import com.example.model.MahasiswaModel;

public interface MahasiswaService {
	List<MahasiswaModel> selectAllMahasiswa();
	
	MahasiswaModel selectMahasiswaByNPM(String npm);
}
