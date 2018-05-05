package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.example.model.JenisSuratModel;

@Mapper
public interface JenisSuratMapper {
	@Select("select * from jenis_surat where id = #{id_jenis_surat}")
	JenisSuratModel selectJenisSurat(@Param("id_jenis_surat") int id_jenis_surat);
	
	@Select("Select nama from jenis_surat where id = #{id_jenis_surat}")
	String getNamaJenisSurat(@Param("id_jenis_surat") int id_jenis_surat);
}