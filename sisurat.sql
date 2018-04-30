-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2018 at 04:07 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sisurat`
--

-- --------------------------------------------------------

--
-- Table structure for table `jenis_surat`
--

CREATE TABLE `jenis_surat` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jenis_surat`
--

INSERT INTO `jenis_surat` (`id`, `nama`) VALUES
(1, 'Surat Izin Ikut Kegiatan'),
(2, 'Surat Izin UTS/UAS'),
(3, 'Surat Keterangan Asisten Dosen'),
(4, 'Surat Keterangan Berkelakuan Baik'),
(5, 'Surat Keterangan Lulus'),
(6, 'Surat Rekomendasi Beasiswa'),
(7, 'Transkrip Nilai\r\n'),
(8, 'Surat Pengantar Peminjaman Buku\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `pengajuan_surat`
--

CREATE TABLE `pengajuan_surat` (
  `id` int(11) NOT NULL,
  `no_surat` varchar(255) DEFAULT NULL,
  `username_pengaju` varchar(255) NOT NULL,
  `tgl_mohon` datetime NOT NULL,
  `id_jenis_surat` int(11) NOT NULL,
  `keterangan` varchar(500) DEFAULT NULL,
  `alasan_izin` varchar(500) DEFAULT NULL,
  `tgl_mulai_izin` datetime DEFAULT NULL,
  `tgl_sls_izin` datetime DEFAULT NULL,
  `id_matkul_terkait` int(11) DEFAULT NULL,
  `username_pegawai` varchar(255) DEFAULT NULL,
  `id_status_surat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `status_surat`
--

CREATE TABLE `status_surat` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status_surat`
--

INSERT INTO `status_surat` (`id`, `nama`) VALUES
(1, 'Baru Diajukan'),
(2, 'Ditolak'),
(3, 'Diproses'),
(4, 'Selesai');

-- --------------------------------------------------------

--
-- Table structure for table `user_account`
--

CREATE TABLE `user_account` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_account`
--

INSERT INTO `user_account` (`username`, `password`, `role`) VALUES
('1506721756', 'password', 'mahasiswa'),
('1506721762', 'password', 'mahasiswa'),
('1506721775', 'password', 'mahasiswa'),
('1506721781', 'password', 'mahasiswa'),
('1506721794', 'password', 'mahasiswa'),
('1506721806', 'password', 'mahasiswa'),
('1506721812', 'password', 'mahasiswa'),
('1506721825', 'password', 'mahasiswa'),
('1506721831', 'password', 'mahasiswa'),
('1506721844', 'password', 'mahasiswa'),
('1506721863', 'password', 'mahasiswa'),
('1506721876', 'password', 'mahasiswa'),
('1506721882', 'password', 'mahasiswa'),
('1506721895', 'password', 'mahasiswa'),
('1506721900', 'password', 'mahasiswa'),
('1506757642', 'password', 'mahasiswa'),
('1506757655', 'password', 'mahasiswa'),
('1506757661', 'password', 'mahasiswa'),
('1506757680', 'password', 'mahasiswa'),
('1506757693', 'password', 'mahasiswa'),
('1290578809', 'password', 'dosen'),
('1290578803', 'password', 'dosen'),
('1290578805', 'password', 'dosen'),
('1290578897', 'password', 'dosen'),
('1290578843', 'password', 'dosen'),
('1290578845', 'password', 'dosen'),
('1290578815', 'password', 'dosen'),
('1290578817', 'password', 'dosen'),
('1290578811', 'password', 'dosen'),
('1290578813', 'password', 'dosen'),
('1290578823', 'password', 'dosen'),
('1290578825', 'password', 'dosen'),
('1290578819', 'password', 'dosen'),
('1290578821', 'password', 'dosen'),
('1290578831', 'password', 'dosen'),
('1290578833', 'password', 'dosen'),
('1290578827', 'password', 'dosen'),
('1290578829', 'password', 'dosen'),
('1290578839', 'password', 'dosen'),
('1290578841', 'password', 'dosen'),
('1290578835', 'password', 'dosen'),
('1290578837', 'password', 'dosen'),
('1290578783', 'password', 'dosen'),
('1290578785', 'password', 'dosen'),
('1290578781', 'password', 'dosen'),
('1290578791', 'password', 'dosen'),
('1290578793', 'password', 'dosen'),
('1290578787', 'password', 'dosen'),
('1290578789', 'password', 'dosen'),
('1290578799', 'password', 'dosen'),
('1290578801', 'password', 'dosen'),
('1290578795', 'password', 'dosen'),
('1290578797', 'password', 'dosen'),
('1290578807', 'password', 'dosen'),
('1506737823', 'password', 'staf'),
('1506689692', 'password', 'staf'),
('1506723231', 'password', 'staf'),
('1406575815', 'password', 'staf');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jenis_surat`
--
ALTER TABLE `jenis_surat`
  ADD KEY `id` (`id`);

--
-- Indexes for table `pengajuan_surat`
--
ALTER TABLE `pengajuan_surat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status_surat`
--
ALTER TABLE `status_surat`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jenis_surat`
--
ALTER TABLE `jenis_surat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `pengajuan_surat`
--
ALTER TABLE `pengajuan_surat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `status_surat`
--
ALTER TABLE `status_surat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
