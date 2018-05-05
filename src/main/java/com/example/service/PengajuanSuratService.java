package com.example.service;
import java.util.List;

import com.example.model.PengajuanSuratModel;

public interface PengajuanSuratService {
	 PengajuanSuratModel getStatusSurat(String no_surat);
	 List<PengajuanSuratModel> selectPengajuan (String username_pengaju);
	 PengajuanSuratModel getDetailPengajuanSurat(int id_pengajuan_surat);
}