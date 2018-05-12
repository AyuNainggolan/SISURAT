package com.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PengajuanSuratModel {
	private int id;
	private String no_surat;
	private String username_pengaju;
	private Date tgl_mohon;
	private int id_jenis_surat;
	private String keterangan;
	private String alasan_izin;
	private String tgl_mulai_izin;
	private String tgl_sls_izin;
	private int id_matkul_terkait;
	private String username_pegawai;
	private int id_status_surat;
	private int status_upload;
	private JenisSuratModel jenis_surat;
	private UserAccountModel accountMahasiswa;
	private UserAccountModel accountPegawai;
	private StatusSuratModel statusSurat;
	
	public String getUsernamePengaju() {
		return username_pengaju;
	}
	
}
