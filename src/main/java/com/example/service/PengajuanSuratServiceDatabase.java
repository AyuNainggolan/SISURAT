package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
  	public List<PengajuanSuratModel> selectPengajuanByDate (String startDate, String endDate){
  		log.info("select * from pengajuan_surat where tgl_mohon between "+startDate+" and "+endDate);
  		return pengajuanSuratMapper.selectPengajuanByDate(startDate, endDate);
  	}
  	
  	@Override
  	public List<PengajuanSuratModel> selectPengajuanByDateMahasiswa (String startDate, String endDate, String name){
  		log.info("select * from pengajuan_surat where tgl_mohon between "+startDate+" and "+endDate);
  		return pengajuanSuratMapper.selectPengajuanByDateMahasiswa(startDate, endDate, name);
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
  	public List<PengajuanSuratModel> selectPengajuanByStatus (String status){
  		log.info("select * from pengajuan_surat where id_status_surat = "+status);
  		return pengajuanSuratMapper.selectPengajuanByStatus(status);
  	}
  	
  	@Override
  	public List<PengajuanSuratModel> selectPengajuanByStatusMahasiswa (String status, String name){
  		log.info("select * from pengajuan_surat where id_status_surat = "+status);
  		return pengajuanSuratMapper.selectPengajuanByStatusMahasiswa(status, name);
  	}
  	
  	@Override
  	public List<PengajuanSuratModel> selectAllStatus (){
  		log.info("select * from pengajuan_surat where id_status_surat = ");
  		return pengajuanSuratMapper.selectAllStatus();
  	}
  
    
    @Override
    public List<PengajuanSuratModel> selectAllPengajuanFilterByJenisMahasiswa (int id_jenis_surat, String name){
        log.info("Filter surat by jenis: "+id_jenis_surat);
        return pengajuanSuratMapper.selectAllPengajuanFilterByJenisMahasiswa(id_jenis_surat, name);
    }
    
  	public List<PengajuanSuratModel> selectAllPengajuanFilterByJenis (int id_jenis_surat, String name){
  		log.info("Filter surat by jenis: "+id_jenis_surat);
  		return pengajuanSuratMapper.selectAllPengajuanFilterByJenis(id_jenis_surat, name);
  	}
}
