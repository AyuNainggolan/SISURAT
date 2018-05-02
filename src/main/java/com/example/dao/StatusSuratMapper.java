package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.StatusSuratModel;

@Mapper
public interface StatusSuratMapper {
	@Select("select * from status_surat where id = #{id_status_surat}")
	StatusSuratModel selectStatusSurat(@Param("id_status_surat") int id_status_surat);
}