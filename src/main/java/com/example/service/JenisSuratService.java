package com.example.service;

import java.util.List;

import com.example.model.JenisSuratModel;

public interface JenisSuratService {
	JenisSuratModel selectJenisSurat(int id_jenis_surat);
	String getNamaJenisSurat (int id_surat);
	List<JenisSuratModel> selectAllJenisSurat();
<<<<<<< HEAD
}
=======
	List<JenisSuratModel> getAllJenisSurat();
}
>>>>>>> b1dfa4b871c7766eb707f0807b6dd19f29f820c7
