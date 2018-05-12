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
  
	@Override
	public PengajuanSuratModel getDetailPengajuanSurat(int id_pengajuan_surat) {
		return pengajuanSuratMapper.getDetailPengajuanSurat(id_pengajuan_surat);
	}
	
	@Override
	public List<PengajuanSuratModel> selectAllPengajuan() {
		return pengajuanSuratMapper.selectAllPengajuan();
	}
  
  	@Override
	public PengajuanSuratModel getStatusSurat(String no_surat) {
		log.info("get status surat with no_surat: ", no_surat);
		return pengajuanSuratMapper.getStatusSurat(no_surat);
	}

	@Override
	public void updateStatusPengajuanSurat(int id_pengajuan_surat, int id_status) {
		
		pengajuanSuratMapper.updateStatusPengajuanSurat(id_pengajuan_surat, id_status);
	}

	@Override
	public String selectNo_surat() {
		return pengajuanSuratMapper.selectNo_surat();
	}

	@Override
	public void addPengajuanSurat(PengajuanSuratModel pengajuanSuratModel) {
		pengajuanSuratMapper.addPengajuanSurat(pengajuanSuratModel);
	}
  	
	@Override
	public int getCountProcessedSurat(int npm) {
		return pengajuanSuratMapper.getCountProcessedSurat(npm);
	}
	
	@Override
	public int getCountFinishedSurat(int npm) {
		return pengajuanSuratMapper.getCountFinishedSurat(npm);
	}
	
	@Override
	public void updateStatusUpload(String id_pengajuan_surat) {
		pengajuanSuratMapper.updateStatusUpload(id_pengajuan_surat);
	} 	
}