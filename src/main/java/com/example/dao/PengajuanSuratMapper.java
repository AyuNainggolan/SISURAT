package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PengajuanSuratModel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;

import com.example.model.JenisSuratModel;
import com.example.model.MahasiswaModel;
import com.example.model.PegawaiModel;
import com.example.model.StatusSuratModel;
import com.example.model.UserAccountModel;

@Mapper
public interface PengajuanSuratMapper {
	
	@Select("SELECT * FROM pengajuan_surat WHERE id = #{id_pengajuan_surat}")
    PengajuanSuratModel getDetailPengajuanSurat (@Param("id_pengajuan_surat") int id_pengajuan_surat);
    
    @Select("select * from pengajuan_surat where username_pengaju=#{username}")
    @Results(value= {
    @Result (property="no_surat", column="no_surat"),
    @Result(property="tgl_mohon", column="tgl_mohon"),
    @Result(property="id_jenis_surat", column="id_jenis_surat"),
    @Result(property="jenis_surat", column="id_jenis_surat", one= @One(select="selectJenisSurat")),
    @Result(property="username_pengaju", column="username_pengaju"),
    @Result(property="accountMahasiswa", column="username_pengaju", one=@One(select="selectUserAccountMhs")),
    @Result(property="username_pegawai", column="username_pegawai"),
    @Result(property="accountPegawai", column="username_pegawai", one=@One(select="selectUserAccountPegawai")),
    @Result(property="id_status_surat", column="id_status_surat"),
    @Result(property="status_upload", column="status_upload"),
    @Result(property="statusSurat", column="id_status_surat", one=@One(select="selectStatusSurat"))})
    List<PengajuanSuratModel> selectPengajuan (@Param("username") String username);
    
    @Select("select * from jenis_surat where id = #{id_jenisSurat}")
    @Results( value = {
    		@Result(property = "id", column = "id"),
            @Result(property = "nama", column = "nama")
    })
    JenisSuratModel selectJenisSurat (@Param("id_jenisSurat") Integer id_jenisSurat);
    
    @Select("select * from user_account where username = #{username_pengaju}")
    @Results( value = {
    		@Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "role", column = "role"),
            //@Result(property = "mhs", column = "username", one = @One(select = "selectMhs"))
    })
    UserAccountModel selectUserAccountMhs (@Param("username_pengaju") String username_pengaju);
    
    @Select("select * from mahasiswa where username = #{username}")
    @Results( value = {
    		@Result(property = "id", column = "id"),
    		@Result(property = "npm", column = "npm"),
    		@Result(property = "username", column = "username"),
    		@Result(property = "nama", column = "nama"),
            @Result(property = "status", column = "status")
    })
    MahasiswaModel selectMhs (@Param("username") String username); 
    
    @Select("select * from user_account where username = #{username_pegawai}")
    @Results( value = {
    		@Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "role", column = "role"),
            //@Result(property = "pegawai", column = "username", one = @One(select = "selectPegawai"))
    })
    UserAccountModel selectUserAccountPegawai (@Param("username_pegawai") String username_pegawai);
    
    @Select("select * from pegawai where username = #{username}")
    @Results( value = {
    		@Result(property = "id", column = "id"),
    		@Result(property = "nip", column = "nip"),
    		@Result(property = "username", column = "username"),
    		@Result(property = "nama", column = "nama"),
            @Result(property = "is_staf", column = "is_staf")
    })
    PegawaiModel selectPegawai (@Param("username") String username);
    
    @Select("select * from status_surat where id = #{id_status_surat}")
    @Results( value = {
    		@Result(property = "id", column = "id"),
            @Result(property = "nama", column = "nama")
    })
    StatusSuratModel selectStatusSurat (@Param("id_status_surat") Integer id_status_surat);
    
    @Select("select * from pengajuan_surat")
    @Results(value= {
    @Result (property="no_surat", column="no_surat"),
    @Result(property="tgl_mohon", column="tgl_mohon"),
    @Result(property="id_jenis_surat", column="id_jenis_surat"),
    @Result(property="jenis_surat", column="id_jenis_surat", one= @One(select="selectJenisSurat")),
    @Result(property="username_pengaju", column="username_pengaju"),
    @Result(property="accountMahasiswa", column="username_pengaju", one=@One(select="selectUserAccountMhs")),
    @Result(property="username_pegawai", column="username_pegawai"),
    @Result(property="accountPegawai", column="username_pegawai", one=@One(select="selectUserAccountPegawai")),
    @Result(property="id_status_surat", column="id_status_surat"),
    @Result(property="status_upload", column="status_upload"),
    @Result(property="statusSurat", column="id_status_surat", one=@One(select="selectStatusSurat"))})
    List<PengajuanSuratModel> selectAllPengajuan ();

    @Select("select * from pengajuan_surat where no_surat = #{no_surat} and id_jenis_surat = 8")
	PengajuanSuratModel getStatusSurat (@Param("no_surat") String no_surat);
    
    @Update("UPDATE pengajuan_surat SET id_status_surat = #{id_status} WHERE id = #{id_pengajuan_surat}")
    void updateStatusPengajuanSurat(@Param("id_pengajuan_surat") int id_pengajuan_surat, @Param("id_status") int id_status);
    
    @Insert("INSERT INTO pengajuan_surat (no_surat, username_pengaju, tgl_mohon, id_jenis_surat, keterangan, alasan_izin, tgl_mulai_izin, tgl_sls_izin, id_matkul_terkait, id_status_surat) "
    		+ "VALUES (#{no_surat}, #{username_pengaju}, #{tgl_mohon}, #{id_jenis_surat}, #{keterangan}, #{alasan_izin}, #{tgl_mulai_izin}, #{tgl_sls_izin}, "
    		+ "#{id_matkul_terkait}, #{id_status_surat})")
    void addPengajuanSurat (PengajuanSuratModel pengajuanSuratModel);
    
    @Select("SELECT no_surat FROM pengajuan_surat ORDER BY ID DESC LIMIT 1")
    String selectNo_surat();
    
    @Select("SELECT COUNT(*) FROM pengajuan_surat WHERE id_status_surat IN (1, 3) AND username_pengaju = #{npm}")
    int getCountProcessedSurat(@Param("npm") int npm);
    
    @Select("SELECT COUNT(*) FROM pengajuan_surat WHERE id_status_surat IN (2, 4) AND username_pengaju = #{npm}")
    int getCountFinishedSurat(@Param("npm") int npm);
    
    @Update("UPDATE pengajuan_surat SET status_upload = 1 WHERE id = #{id_pengajuan_surat}")
    void updateStatusUpload(@Param("id_pengajuan_surat") String id_pengajuan_surat);
}