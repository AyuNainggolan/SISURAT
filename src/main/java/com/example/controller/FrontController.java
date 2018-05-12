package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.MahasiswaModel;
import com.example.model.MataKuliahModel;
import com.example.model.PegawaiModel;
import com.example.model.PengajuanSuratModel;
import com.example.service.JenisSuratService;
import com.example.service.MahasiswaService;
import com.example.service.MataKuliahService;
import com.example.service.PegawaiService;
import com.example.service.PengajuanSuratService;
import com.example.service.StatusSuratService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FrontController {
	
    @Autowired
    MahasiswaService studentDAO;
    
    @Autowired
	PengajuanSuratService pengajuanSuratDAO;
    
    @Autowired
    PegawaiService pegawaiDAO;
    
    @Autowired
    JenisSuratService jenisSuratDAO;
    
    @Autowired
    StatusSuratService statusSuratDAO;
    
    @Autowired
    MataKuliahService matkulDAO;
    
    @Autowired
    ServletContext context;
    
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
    @RequestMapping("/pengajuan/riwayat")
    public String viewPengajuanSurat (Model model)
    {
    	String namaMahasiswa, namaPegawai;
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
    	
    	List<PengajuanSuratModel> letter = pengajuanSuratDAO.selectPengajuan(name);
    	for(int i=0;i<letter.size();i++) {
    		namaMahasiswa = searchName(letter.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(letter.get(i).getUsername_pegawai());
    		letter.get(i).setUsername_pengaju(namaMahasiswa);
    		letter.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("letter", letter);
    	model.addAttribute("finished_surat", pengajuanSuratDAO.getCountFinishedSurat(Integer.parseInt(name)));
    	model.addAttribute("processed_surat", pengajuanSuratDAO.getCountProcessedSurat(Integer.parseInt(name)));
    	return "riwayatSurat";
    }
    
    @RequestMapping("/pengajuan/viewall")
    public String viewAllPengajuanSurat(Model model) {
    	String namaMahasiswa, namaPegawai;
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectAllPengajuan();
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		System.out.println("nama pegawai "+namaPegawai);
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("lstSurat", lstSurat);
    	return "viewAllPengajuanSurat";
    }
    
    public String searchName(String npm) {
    	MahasiswaModel lstMahasiswa = studentDAO.selectMahasiswaByNPM(npm);
    	return lstMahasiswa.getNama();
    }
    
    public String searchNamaPegawai(String nip) {
    	log.info("pegawai "+ nip);
    	PegawaiModel lstPegawai = pegawaiDAO.selectPegawaiByNIP(nip);
    	return lstPegawai.getNama();
    }
    
    @RequestMapping("/login")
	public String login() {
		return "index";
    }
    
	@RequestMapping("/listSurat")
	public String listSurat() {
		return "listSurat";
	}
	
	@RequestMapping("/pengajuan/view/{id_pengajuan_surat}")
	public String detailPengajuanSurat(Model model, @PathVariable(value = "id_pengajuan_surat") int id_pengajuan_surat) {
		PengajuanSuratModel surat = pengajuanSuratDAO.getDetailPengajuanSurat(id_pengajuan_surat);
		MataKuliahModel matkul = matkulDAO.getMatakuliahById(surat.getId_matkul_terkait());
		String npm = surat.getUsername_pengaju();
		
		model.addAttribute("surat", surat);
		model.addAttribute("nama", this.searchName(npm));
		model.addAttribute("jenis_surat", jenisSuratDAO.selectJenisSurat(surat.getId_jenis_surat()).getNama());
		model.addAttribute("nama_admin", this.searchNamaPegawai(surat.getUsername_pegawai()));
		model.addAttribute("status_surat", statusSuratDAO.getStatusSurat(surat.getId_status_surat()));
		model.addAttribute("nama_matkul", matkul.getNama_matkul());
		model.addAttribute("finished_surat", pengajuanSuratDAO.getCountFinishedSurat(Integer.parseInt(npm)));
    	model.addAttribute("processed_surat", pengajuanSuratDAO.getCountProcessedSurat(Integer.parseInt(npm)));
		return "detailPengajuanSurat";
	}
	
	@RequestMapping("/pengajuan/ubah/{id_pengajuan_surat}/{id_status}")
	public String updateStatusSurat(Model model, 
			@PathVariable(value = "id_pengajuan_surat") int id_pengajuan_surat, 
			@PathVariable(value = "id_status") int id_status, HttpServletRequest request) {
		
		pengajuanSuratDAO.updateStatusPengajuanSurat(id_pengajuan_surat, id_status);
		String referer = request.getHeader("Referer");
		return "redirect:"+ referer;
	}
	
	@RequestMapping(value="/pengajuan/upload", method=RequestMethod.POST, consumes = "multipart/form-data")
    public String handleFileUpload(@RequestBody MultipartFile[] file, HttpServletRequest request, RedirectAttributes ra){
		String referer = request.getHeader("Referer");
		String split[] = referer.split("\\/");
		String id_surat = split[split.length-1];
		String contentType = file[0].getContentType();
		
		log.info("file content type "+file[0].getContentType());
		
		if(!contentType.equals("application/pdf")) {
			log.info("masuk sini");
			String msg = "Gagal! Hanya file format PDF yang bisa di upload!";
            ra.addFlashAttribute("error", msg);
            return "redirect:"+referer;
		}
		String rootPath = System.getProperty("user.dir");
		String file_location = rootPath + File.separator + "src"+File.separator+"main"+File.separator+"resources"+File.separator+"uploads"+File.separator;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
		 if (file[0].isEmpty()) {
			 String msg = "File kosong";
	            ra.addFlashAttribute("error", msg);
	            return "redirect:"+referer;
	        }

	        try {
	            byte[] bytes = file[0].getBytes();
	            Path path = Paths.get(file_location+ name + "_"+ id_surat);
	            log.info(path.toString());
	            Files.write(path, bytes);
	            String msg = "Berkas surat berhasil di unggah.";
	            ra.addFlashAttribute("sukses", msg);

	        } catch (IOException e) {
	        	
	            e.printStackTrace();
	            String msg = "Terdapat kesalahan System. Coba beberapa saat lagi!";
	            ra.addFlashAttribute("error", msg);
	        }
		
	        return "redirect:"+referer;
    }
	
	@RequestMapping(value = "/pengajuan/download/{id_pengajuan_surat}", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String param, @PathVariable(value = "id_pengajuan_surat") int id_pengajuan_surat) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String rootPath = System.getProperty("user.dir");
		String file_location = rootPath + File.separator + "src"+File.separator+"main"+File.separator+"resources"+File.separator+"uploads"+File.separator;
		String fileName = auth.getName() + "_"+ id_pengajuan_surat+".pdf";
		
        String name = auth.getName(); //get logged in username
        
		File downloadedFile = new File(file_location + name + "_"+ id_pengajuan_surat);
		Path path = Paths.get(downloadedFile.getAbsolutePath());
        HttpHeaders headers = new HttpHeaders();
        
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        System.out.println("The length of the file is : "+downloadedFile.length());
        
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	    
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(downloadedFile.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}
}