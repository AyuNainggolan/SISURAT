package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PengajuanSuratMapper;
import com.example.model.PengajuanSuratModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PengajuanSuratServiceDatabase implements PengajuanSuratService{
	@Autowired
    private PengajuanSuratMapper pengajuanSuratMapper;

	@Override
	public List<PengajuanSuratModel> selectPengajuan(String username_pengaju) {
		log.info ("select all username_pengaju");
		return pengajuanSuratMapper.selectPengajuan(username_pengaju);
	}

	

}
