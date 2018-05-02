package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.PengajuanSuratModel;
import com.example.service.PengajuanSuratService;

@RestController
@RequestMapping("/rest")
public class SuratRestController {
	@Autowired
	PengajuanSuratService pengajuanSuratService;
	
	@RequestMapping("/surat/view/{no_surat}")
	public PengajuanSuratModel view (@PathVariable(value="no_surat") String no_surat) {
		PengajuanSuratModel surat = pengajuanSuratService.getStatusSurat(no_surat);
		return surat;
	}
//	
//	@RequestMapping("/student/viewall")
//	public List<StudentModel> viewall () {
//		List<StudentModel> student = studentService.selectAllStudents();
//		return student;
//	}
}
