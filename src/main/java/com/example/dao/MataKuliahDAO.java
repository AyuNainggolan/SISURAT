package com.example.dao;

import java.util.List;

import com.example.model.MataKuliahModel;

public interface MataKuliahDAO {
	
	MataKuliahModel getMatakuliahById(int id);
	List<MataKuliahModel> getAllMatakuliah();
}
