package com.example.service;
import java.util.List;

import com.example.model.PengajuanSuratModel;

public interface PengajuanSuratService {
	List<PengajuanSuratModel> selectAllPengajuan();
	List<PengajuanSuratModel> selectPengajuan (String username_pengaju);

	PengajuanSuratModel getDetailPengajuanSurat(int id_pengajuan_surat);
}

