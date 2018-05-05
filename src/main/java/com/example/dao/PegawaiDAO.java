package com.example.dao;
import com.example.model.PegawaiModel;

public interface PegawaiDAO {
	PegawaiModel selectPegawaiByNIP(String nip);
}
