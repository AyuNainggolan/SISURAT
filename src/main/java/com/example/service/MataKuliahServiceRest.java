package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.dao.MataKuliahDAO;
import com.example.model.MataKuliahModel;

@Service
@Primary
public class MataKuliahServiceRest implements MataKuliahService {
	
	@Autowired
	private MataKuliahDAO matkulDAO;
	
	@Override
	public MataKuliahModel getMatakuliahById(int id) {
		return matkulDAO.getMatakuliahById(id);
	}

}
