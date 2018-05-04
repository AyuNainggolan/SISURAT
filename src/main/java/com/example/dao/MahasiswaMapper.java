package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.MahasiswaModel;

@Mapper
public interface MahasiswaMapper {
//	@Select("Select nama from mahasiswa where username = #{username_pengaju}")
//	String getNamaMhs(@Param("username_pengaju") String username_pengaju);
//	
//	@Select("Select npm from mahasiswa where username = #{username_pengaju}")
//	String getNPMMhs(@Param("username_pengaju") String username_pengaju);
}
