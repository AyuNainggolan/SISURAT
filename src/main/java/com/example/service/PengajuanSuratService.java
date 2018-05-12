package com.example.service;
import java.util.List;

import com.example.model.PengajuanSuratModel;

public interface PengajuanSuratService {
	 List<PengajuanSuratModel> selectAllPengajuan();
	 PengajuanSuratModel getStatusSurat(String no_surat);
	 List<PengajuanSuratModel> selectPengajuan (String username_pengaju);
	 PengajuanSuratModel getDetailPengajuanSurat(int id_pengajuan_surat);
	 void updateStatusPengajuanSurat(int id_pengajuan_surat, int id_status);
	 List<PengajuanSuratModel> selectAllPengajuanFilterByJenis (int id_jenis_surat, String name);
}
