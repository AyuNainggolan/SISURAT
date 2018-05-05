package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSuratModel {
	private int id_mahasiswa;
	private String jenis_surat;
	private String keterangan_surat;
	private String status_surat;
}