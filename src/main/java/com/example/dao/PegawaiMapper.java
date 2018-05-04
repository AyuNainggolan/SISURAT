package com.example.dao;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PegawaiModel;
@Mapper
public interface PegawaiMapper {
	@Select("Select nama from pegawai where username = #{username_pegawai}")
	String getNamaPegawai(@Param("username_pegawai") String username_pegawai);
}
