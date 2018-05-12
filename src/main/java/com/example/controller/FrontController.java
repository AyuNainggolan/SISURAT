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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.JenisSuratModel;
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
    	List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
    	List<PengajuanSuratModel> lstStatus = pengajuanSuratDAO.selectAllStatus();
    	String namaMahasiswa, namaPegawai;
    	log.info("Test");
    
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
    	log.info("user logged in"+ name);
    	List<PengajuanSuratModel> letter = pengajuanSuratDAO.selectPengajuan(name);
    	
    	for(int i=0;i<letter.size();i++) {
    		namaMahasiswa = searchName(letter.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(letter.get(i).getUsername_pegawai());
    		letter.get(i).setUsername_pengaju(namaMahasiswa);
    		letter.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("letter", letter);
    	model.addAttribute("jenisSurat", allJenisSurat);
    	model.addAttribute("lstStatus", lstStatus);
    	return "riwayatSurat";
    }
    
    @RequestMapping("/pengajuan/riwayat/filterByDate/{tanggalAwal}/{tanggalAkhir}")
    public String filterByDateMahasiswa(Model model, @PathVariable(value = "tanggalAwal") String tanggalAwal, @PathVariable(value="tanggalAkhir") String tanggalAkhir) {
    	String namaMahasiswa, namaPegawai;
    	log.info("awal "+tanggalAwal);
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
    	log.info("user logged in"+ name);
    	List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectPengajuanByDateMahasiswa(tanggalAwal, tanggalAkhir, name);
    	List<PengajuanSuratModel> lstStatus = pengajuanSuratDAO.selectAllStatus();
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		System.out.println("nama pegawai "+namaPegawai);
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    	}
    	model.addAttribute("letter", lstSurat);
    	model.addAttribute("jenisSurat", allJenisSurat);
    	model.addAttribute("lstStatus", lstStatus);
    	return "riwayatSurat";
    }
    
    @RequestMapping("/pengajuan/riwayat/filterByStatus/{status}")
    public String filterByStatusMahasiswa(Model model, @PathVariable(value = "status") String status) {
    	String namaMahasiswa, namaPegawai;
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
    	log.info("user logged in"+ name);
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectPengajuanByStatusMahasiswa(status, name);
    	List<PengajuanSuratModel> lstStatus = pengajuanSuratDAO.selectAllStatus();
    	List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		System.out.println("nama pegawai "+namaPegawai);
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("letter", lstSurat);
    	model.addAttribute("lstStatus", lstStatus);
    	model.addAttribute("jenisSurat", allJenisSurat);

    	return "riwayatSurat";
    }
    
    @RequestMapping("pengajuan/riwayat/filterByJenis/{jenis}")
    public String filterByJenisMahasiswa(Model model, @PathVariable(value = "jenis") int jenisSurat) {
		List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
		List<PengajuanSuratModel> lstStatus = pengajuanSuratDAO.selectAllStatus();
    	String namaMahasiswa, namaPegawai;
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectAllPengajuanFilterByJenisMahasiswa(jenisSurat, name);
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("lstSurat", lstSurat);
    	model.addAttribute("jenisSurat", allJenisSurat);
    	model.addAttribute("lstStatus", lstStatus);
    	return "riwayatSurat";
    }
    
    @RequestMapping("/pengajuan/viewall")
    public String viewAllPengajuanSurat(Model model) {
    	List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
    	String namaMahasiswa, namaPegawai;
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectAllPengajuan();
    	List<PengajuanSuratModel> lstStatus = pengajuanSuratDAO.selectAllStatus();
    	List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		System.out.println("nama pegawai "+namaPegawai);
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    		log.info("id status surat "+lstSurat.get(i).getJenis_surat().getId());
    		log.info("nama status surat "+lstSurat.get(i).getJenis_surat().getNama());
    	} 
    	model.addAttribute("lstSurat", lstSurat);
    	model.addAttribute("lstStatus", lstStatus);
    	model.addAttribute("jenisSurat", allJenisSurat);
    	return "viewAllPengajuanSurat";
    }
    
    @RequestMapping("/pengajuan/viewall/filterByDate/{tanggalAwal}/{tanggalAkhir}")
    public String filterByDate(Model model, @PathVariable(value = "tanggalAwal") String tanggalAwal, @PathVariable(value="tanggalAkhir") String tanggalAkhir) {
    	String namaMahasiswa, namaPegawai;
    	log.info("awal "+tanggalAwal);
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectPengajuanByDate(tanggalAwal, tanggalAkhir);
    	List<PengajuanSuratModel> lstStatus = pengajuanSuratDAO.selectAllStatus();
    	List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		System.out.println("nama pegawai "+namaPegawai);
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    	}
    	model.addAttribute("lstSurat", lstSurat);
    	model.addAttribute("lstStatus", lstStatus);
    	model.addAttribute("jenisSurat", allJenisSurat);
    	return "viewAllPengajuanSurat";
    }
    
    @RequestMapping("/pengajuan/viewall/filterByStatus/{status}")
    public String filterByStatus(Model model, @PathVariable(value = "status") String status) {
    	String namaMahasiswa, namaPegawai;
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectPengajuanByStatus(status);
    	List<PengajuanSuratModel> lstStatus = pengajuanSuratDAO.selectAllStatus();
    	List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		System.out.println("nama pegawai "+namaPegawai);
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("lstSurat", lstSurat);
    	model.addAttribute("lstStatus", lstStatus);
    	model.addAttribute("jenisSurat", allJenisSurat);
    	model.addAttribute("jenisSurat", allJenisSurat);
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
		log.info("ini status surat "+statusSuratDAO.getStatusSurat(surat.getId_status_surat()));
		model.addAttribute("surat", surat);
		model.addAttribute("nama", this.searchName(npm));
		model.addAttribute("jenis_surat", jenisSuratDAO.selectJenisSurat(surat.getId_jenis_surat()).getNama());
		model.addAttribute("nama_admin", this.searchNamaPegawai(surat.getUsername_pegawai()));
		model.addAttribute("status_surat", statusSuratDAO.getStatusSurat(surat.getId_status_surat()));
		model.addAttribute("nama_matkul", matkul.getNama_matkul());
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
    public String handleFileUpload(@RequestBody MultipartFile[] file, HttpServletRequest request){
		String referer = request.getHeader("Referer");
		String split[] = referer.split("\\/");
		String id_surat = split[split.length-1];
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
		 if (file[0].isEmpty()) {
	           log.info("file is empty");
	        }

	        try {
	            byte[] bytes = file[0].getBytes();
	            Path path = Paths.get(context.getRealPath("uploads") +"_"+ auth.getName() + "_"+ id_surat);
	            Files.write(path, bytes);
	            log.info(context.getRealPath("uploads") +"_"+ auth.getName() + "_"+ id_surat);
	            log.info("Done Uploaded");

	        } catch (IOException e) {
	        	
	            e.printStackTrace();
	        }
		
        return "listSurat";
    }
	
	@RequestMapping(value = "/pengajuan/download/{id_pengajuan_surat}", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String param, @PathVariable(value = "id_pengajuan_surat") int id_pengajuan_surat) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String fileName = auth.getName() + "_"+ id_pengajuan_surat+".pdf";
	
		File downloadedFile = new File("D:\\"+fileName);
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
	
	@RequestMapping("pengajuan/viewall/filterByJenis/{jenis}")
    public String filterByJenis(Model model, @PathVariable(value = "jenis") int jenisSurat) {
		List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
		List<PengajuanSuratModel> lstStatus = pengajuanSuratDAO.selectAllStatus();
	@RequestMapping(value="/pengajuan/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
		String name = "C:\\Users\\Bukalapak\\Documents\\PROJECT\\SISURAT";
				
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
	
	@RequestMapping("pengajuan/viewall/filterByJenis")
    public String filterByJenis(Model model, @RequestParam(value = "jenis") int jenisSurat) {
		List<JenisSuratModel> allJenisSurat = jenisSuratDAO.selectAllJenisSurat();
    	String namaMahasiswa, namaPegawai;
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
    	List<PengajuanSuratModel> lstSurat = pengajuanSuratDAO.selectAllPengajuanFilterByJenis(jenisSurat, name);
    	for(int i=0;i<lstSurat.size();i++) {
    		namaMahasiswa = searchName(lstSurat.get(i).getUsername_pengaju());
    		namaPegawai = searchNamaPegawai(lstSurat.get(i).getUsername_pegawai());
    		lstSurat.get(i).setUsername_pengaju(namaMahasiswa);
    		lstSurat.get(i).setUsername_pegawai(namaPegawai);
    	} 
    	model.addAttribute("lstSurat", lstSurat);
    	model.addAttribute("jenisSurat", allJenisSurat);
    	model.addAttribute("lstStatus", lstStatus);
    	return "viewAllPengajuanSurat";
    }
}
