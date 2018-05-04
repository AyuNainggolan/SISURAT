package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;

import com.example.model.JenisSuratModel;
import com.example.model.MahasiswaModel;
import com.example.model.PegawaiModel;
import com.example.model.PengajuanSuratModel;
import com.example.model.StatusSuratModel;
import com.example.model.UserAccountModel;

@Mapper
public interface PengajuanSuratMapper {
//    @Select("select id,no_surat,username_pengaju, tgl_mohon, id_jenis_surat, keterangan, alasan_izin, tgl_mulai_izin, tgl_sls_izin, id_matkul_terkait, username_pegawai, id_status_surat from pengajuan_surat")
//    List<PengajuanSuratModel>selectAllPengajuan();
    
//    @Select("select id,no_surat,username_pengaju, tgl_mohon, id_jenis_surat, keterangan, alasan_izin, tgl_mulai_izin, tgl_sls_izin, id_matkul_terkait, username_pegawai, id_status_surat from pengajuan_surat where username_pengaju=#{username}")
//    List<PengajuanSuratModel> selectPengajuan (@Param("username") String username);
    
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
            @Result(property = "mhs", column = "username", one = @One(select = "selectMhs"))
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
            @Result(property = "pegawai", column = "username", one = @One(select = "selectPegawai"))
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
}
