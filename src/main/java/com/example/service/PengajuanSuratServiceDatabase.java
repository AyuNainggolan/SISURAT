package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PengajuanSuratMapper;
import com.example.model.PengajuanSuratModel;

@Service
public class PengajuanSuratServiceDatabase implements PengajuanSuratService{
	
	@Autowired
    private PengajuanSuratMapper pengajuanSurat;
	
	@Override
	public PengajuanSuratModel getDetailPengajuanSurat(int id_pengajuan_surat) {
		
		return pengajuanSurat.getDetailPengajuanSurat(id_pengajuan_surat);
	}

}
