package com.example.service;

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
	public PengajuanSuratModel getStatusSurat(String no_surat) {
		log.info("get status surat with no_surat: ", no_surat);
		return pengajuanSuratMapper.getStatusSurat(no_surat);
	}
}
