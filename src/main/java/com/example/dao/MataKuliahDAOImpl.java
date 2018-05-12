package com.example.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.MahasiswaModel;
import com.example.model.MataKuliahModel;

@Service
public class MataKuliahDAOImpl implements MataKuliahDAO{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public MataKuliahModel getMatakuliahById(int id) {
		MataKuliahModel matkul = restTemplate.getForObject("https://apap-fasilkom.herokuapp.com/api/matkul/view/id/"+id, MataKuliahModel.class);
		
		return matkul;
	}

	@Override
	public List<MataKuliahModel> getAllMatakuliah() {
		MataKuliahModel[] matkul = restTemplate.getForObject("https://apap-fasilkom.herokuapp.com/api/matkul/viewall/", MataKuliahModel[].class);
		List<MataKuliahModel> list_matkul = Arrays.asList(matkul);
		return list_matkul;
	}

}
