package com.example.service;

import com.example.model.JenisSuratModel;

public interface JenisSuratService {
	JenisSuratModel selectJenisSurat(int id_jenis_surat);
	String getNamaJenisSurat (int id_surat);
}