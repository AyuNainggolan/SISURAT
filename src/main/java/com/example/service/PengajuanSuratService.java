package com.example.service;
import java.util.List;

import com.example.model.PengajuanSuratModel;

public interface PengajuanSuratService {
	 List<PengajuanSuratModel> selectAllPengajuan();
	 PengajuanSuratModel getStatusSurat(String no_surat);
	 List<PengajuanSuratModel> selectPengajuan (String username_pengaju);
	 PengajuanSuratModel getDetailPengajuanSurat(int id_pengajuan_surat);
	 void updateStatusPengajuanSurat(int id_pengajuan_surat, int id_status);
	 void addPengajuanSurat (PengajuanSuratModel pengajuanSuratModel);
	 String selectNo_surat();
	 int getCountProcessedSurat(int npm);
	 int getCountFinishedSurat(int npm);
	 void updateStatusUpload(String id_pengajuan_surat);
}
