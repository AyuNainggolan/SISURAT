package com.example.service;

import java.util.List;

import com.example.model.MataKuliahModel;

public interface MataKuliahService {
	MataKuliahModel getMatakuliahById(int id);
	List<MataKuliahModel> getAllMatakuliah();
}
