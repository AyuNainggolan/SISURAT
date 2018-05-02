package com.example.service;

import org.apache.ibatis.annotations.Param;

import com.example.model.StatusSuratModel;

public interface StatusSuratService {
	StatusSuratModel selectStatusSurat(int id_status_surat);
}