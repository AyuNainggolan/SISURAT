package com.example.dao;

import java.util.List;

import com.example.model.MahasiswaModel;

public interface MahasiswaDAO {
	List<MahasiswaModel> selectAllMahasiswa();
	
	MahasiswaModel selectMahasiswaByNPM(String npm);
}
