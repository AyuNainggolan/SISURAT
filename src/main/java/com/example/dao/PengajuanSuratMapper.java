package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.example.model.PengajuanSuratModel;

@Mapper
public interface PengajuanSuratMapper {
	 @Select("select * from pengajuan_surat where no_surat = #{no_surat} and id_jenis_surat = 8")
	 PengajuanSuratModel getStatusSurat (@Param("no_surat") String no_surat);
	 
	 
}