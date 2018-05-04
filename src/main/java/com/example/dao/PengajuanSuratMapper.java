package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.PengajuanSuratModel;

@Mapper
public interface PengajuanSuratMapper {
	
	@Select("SELECT * FROM pengajuan_surat WHERE id = #{id_pengajuan_surat}")
    PengajuanSuratModel getDetailPengajuanSurat (@Param("id_pengajuan_surat") int id_pengajuan_surat);

}
