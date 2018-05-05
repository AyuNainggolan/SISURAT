/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.21-log : Database - sisurat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sisurat` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `sisurat`;

/*Table structure for table `jenis_surat` */

DROP TABLE IF EXISTS `jenis_surat`;

CREATE TABLE `jenis_surat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `jenis_surat` */

/*Table structure for table `pengajuan_surat` */

DROP TABLE IF EXISTS `pengajuan_surat`;

CREATE TABLE `pengajuan_surat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  `id_status_surat` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pengajuan_surat` */

/*Table structure for table `status_surat` */

DROP TABLE IF EXISTS `status_surat`;

CREATE TABLE `status_surat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `status_surat` */

/*Table structure for table `user_account` */

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_account` */

insert  into `user_account`(`username`,`password`,`role`) values ('1506721756','password','mahasiswa'),('1506721762','password','mahasiswa'),('1506721775','password','mahasiswa'),('1506721781','password','mahasiswa'),('1506721794','password','mahasiswa'),('1506721806','password','mahasiswa'),('1506721812','password','mahasiswa'),('1506721825','password','mahasiswa'),('1506721831','password','mahasiswa'),('1506721844','password','mahasiswa'),('1506721863','password','mahasiswa'),('1506721876','password','mahasiswa'),('1506721882','password','mahasiswa'),('1506721895','password','mahasiswa'),('1506721900','password','mahasiswa'),('1506757642','password','mahasiswa'),('1506757655','password','mahasiswa'),('1506757661','password','mahasiswa'),('1506757680','password','mahasiswa'),('1506757693','password','mahasiswa'),('1290578809','password','dosen'),('1290578803','password','dosen'),('1290578805','password','dosen'),('1290578897','password','dosen'),('1290578843','password','dosen'),('1290578845','password','dosen'),('1290578815','password','dosen'),('1290578817','password','dosen'),('1290578811','password','dosen'),('1290578813','password','dosen'),('1290578823','password','dosen'),('1290578825','password','dosen'),('1290578819','password','dosen'),('1290578821','password','dosen'),('1290578831','password','dosen'),('1290578833','password','dosen'),('1290578827','password','dosen'),('1290578829','password','dosen'),('1290578839','password','dosen'),('1290578841','password','dosen'),('1290578835','password','dosen'),('1290578837','password','dosen'),('1290578783','password','dosen'),('1290578785','password','dosen'),('1290578781','password','dosen'),('1290578791','password','dosen'),('1290578793','password','dosen'),('1290578787','password','dosen'),('1290578789','password','dosen'),('1290578799','password','dosen'),('1290578801','password','dosen'),('1290578795','password','dosen'),('1290578797','password','dosen'),('1290578807','password','dosen'),('1506737823','password','staf'),('1506689692','password','staf'),('1506723231','password','staf'),('1406575815','password','staf');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
