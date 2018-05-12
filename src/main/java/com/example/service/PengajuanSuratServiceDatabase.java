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
  	public List<PengajuanSuratModel> selectPengajuanByDate (String startDate, String endDate){
  		log.info("select * from pengajuan_surat where tgl_mohon between "+startDate+" and "+endDate);
  		return pengajuanSuratMapper.selectPengajuanByDate(startDate, endDate);
  	}
  	
  	@Override
  	public List<PengajuanSuratModel> selectPengajuanByStatus (String status){
  		log.info("select * from pengajuan_surat where id_status_surat = "+status);
  		return pengajuanSuratMapper.selectPengajuanByStatus(status);
  	}
  	
  	@Override
  	public List<PengajuanSuratModel> selectAllStatus (){
  		log.info("select * from pengajuan_surat where id_status_surat = ");
  		return pengajuanSuratMapper.selectAllStatus();
  	}
  
    @Override
    public void updateStatusPengajuanSurat(int id_pengajuan_surat, int id_status) {

      pengajuanSuratMapper.updateStatusPengajuanSurat(id_pengajuan_surat, id_status);
    }

    @Override
    public List<PengajuanSuratModel> selectAllPengajuanFilterByJenis (int id_jenis_surat, String name){
        log.info("Filter surat by jenis: "+id_jenis_surat);
        return pengajuanSuratMapper.selectAllPengajuanFilterByJenis(id_jenis_surat, name);
    }
}
