-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 21, 2019 lúc 11:55 AM
-- Phiên bản máy phục vụ: 10.1.37-MariaDB
-- Phiên bản PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `db_quanlynhathuoc`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHoaDon` int NOT NULL,
  `MaThuoc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaDonViTinh` int(10) UNSIGNED NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaPhieuNhap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaThuoc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaDonViTinh` int(10) UNSIGNED NOT NULL,
  `SoLuong` int(10) UNSIGNED NOT NULL,
  `DonGia` float UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donvitinh`
--

CREATE TABLE `donvitinh`(
  `MaDonViTinh` int(10) UNSIGNED NOT NULL,
  `TenDonViTinh` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHoaDon` int AUTO_INCREMENT NOT NULL,
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaKhachHang` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaKhuyenMai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayLap` date NOT NULL,
  `GioLap` time NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKhachHang` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenKhachHang` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `SoDienThoai` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `MaHuyen` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `huyen`
--

CREATE TABLE `huyen`(
  `MaHuyen` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TenHuyen` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaTinh` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tinh`
--

CREATE TABLE `tinh`(
  `MaTinh` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TenTinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKhuyenMai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenKhuyenMai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DieuKienKhuyenMai` float NOT NULL,
  `PhanTramKhuyenMai` float NOT NULL,
  `NgayBatDau` date DEFAULT NULL,
  `NgayKetThuc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaithuoc`
--

CREATE TABLE `loaithuoc` (
  `MaLoaiThuoc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoaiThuoc` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` archar(65535) COLLATE utf8_unicode_ci NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNhaCungCap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenNhaCungCap` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `MaHuyen` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Fax` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenNhanVien` text COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `MaHuyen` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaQuyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenQuyen` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ChiTietQuyen` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPhieuNhap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhaCungCap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayNhap` date NOT NULL,
  `GioNhap` time NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuoc`
--

CREATE TABLE `thuoc`(
  `MaThuoc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaLoaiThuoc` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `TenThuoc` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` varchar(65535) COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` float NOT NULL,
  `MaDonViTinh` int(10) UNSIGNED NOT NULL,
  `SoLuong` int(10) UNSIGNED NOT NULL DEFAULT '1',
  `HinhAnh` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TenTaiKhoan` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhanVien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaQuyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Cấu trúc bảng cho bảng kho
--
CREATE TABLE kho ( 
	MaThuoc varchar(10) COLLATE utf8_unicode_ci NOT NULL, 
	MaLoaiThuoc varchar(30) COLLATE utf8_unicode_ci NOT NULL, 
	MaDonViTinh int(10) UNSIGNED NOT NULL, 
	SoLuongConLai int NOT NULL, 
	TinhTrang varchar(20) COLLATE utf8_unicode_ci NOT NULL 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Tạo các ràng buộc cho các bảng
--

--
-- Tạo ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE chitiethoadon
  ADD PRIMARY KEY (MaHoaDon, MaThuoc)

--
-- Khóa, Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD KEY `MaThuoc` (`MaThuoc`), 
  ADD KEY `MaPhieuNhap` (`MaPhieuNhap`);

--
-- Khóa, Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHoaDon`),
  ADD KEY `MaNhanVien` (`MaNhanVien`),
  ADD KEY `MaKhachHang` (`MaKhachHang`),
  ADD KEY `MaKhuyenMai` (`MaKhuyenMai`);

--
-- Khóa, Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKhachHang`);

--
-- Khóa, Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKhuyenMai`);

--
-- Khóa, Chỉ mục cho bảng `loaithuoc`
--
ALTER TABLE `loaithuoc`
  ADD PRIMARY KEY (`MaLoaiThuoc`);

--
-- Khóa, Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNhaCungCap`),
  ADD KEY (`MaHuyen`);

--
-- Khóa, Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNhanVien`),
  ADD KEY (`MaHuyen`);

--
-- Khóa, Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Khóa, Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPhieuNhap`),
  ADD KEY `MaNhaCungCap` (`MaNhaCungCap`),
  ADD KEY `MaNhanVien` (`MaNhanVien`);

--
-- Khóa, Chỉ mục cho bảng `thuoc`
--
ALTER TABLE `thuoc`
  ADD PRIMARY KEY (`MaThuoc`),
  ADD KEY `MaLoaiThuoc` (`MaLoaiThuoc`);

--
-- Khóa, Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TenTaiKhoan`),
  ADD KEY `MaQuyen` (`MaQuyen`),
  ADD KEY `MaNhanVien` (`MaNhanVien`);

--
-- Khóa, Chỉ mục cho bảng `donvitinh`
--
ALTER TABLE `donvitinh` ADD PRIMARY KEY(`MaDonViTinh`);

--
-- Khóa, Chỉ mục cho bảng `huyen`
--
ALTER TABLE `huyen` 
  ADD PRIMARY KEY(`MaHuyen`);
  ADD KEY(`MaTinh`);

--
-- Khóa, Chỉ mục cho bảng `tinh`
--
ALTER TABLE `tinh` ADD PRIMARY KEY(`MaTinh`);

--
-- Khóa, Chỉ mục cho bảng `kho`
--
ALTER TABLE kho
  ADD PRIMARY KEY (MaThuoc, MaLoaiThuoc);

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`) ON UPDATE CASCADE,
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaThuoc`) REFERENCES `thuoc` (`MaThuoc`) ON UPDATE CASCADE,
  ADD CONSTRAINT `chitiethoadon_ibfk_3` FOREIGN KEY (`MaDonViTinh`) REFERENCES `donvitinh` (`MaDonViTinh`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaThuoc`) REFERENCES `thuoc` (`MaThuoc`) ON UPDATE CASCADE,
  ADD CONSTRAINT `chitietphieunhap_ibfk_3` FOREIGN KEY (`MaPhieuNhap`) REFERENCES `phieunhap` (`MaPhieuNhap`) ON UPDATE CASCADE,
  ADD CONSTRAINT `chitietphieunhap_ibfk_4` FOREIGN KEY (`MaDonViTinh`) REFERENCES `donvitinh` (`MaDonViTinh`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`) ON UPDATE CASCADE,
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON UPDATE CASCADE,
  ADD CONSTRAINT `khuyenmai_ibfk_3` FOREIGN KEY (`MaKhuyenMai`) REFERENCES `khuyenmai` (`MaKhuyenMai`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON UPDATE CASCADE,
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `thuoc`
--
ALTER TABLE `thuoc`
  ADD CONSTRAINT `thuoc_ibfk_1` FOREIGN KEY (`MaLoaiThuoc`) REFERENCES `loaithuoc` (`MaLoaiThuoc`) ON UPDATE CASCADE,
  ADD CONSTRAINT `thuoc_ibfk_2` FOREIGN KEY (`MaDonViTinh`) REFERENCES `donvitinh` (`MaDonViTinh`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON UPDATE CASCADE,
  ADD CONSTRAINT `taikhoan_ibfk_3` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `huyen`
--
ALTER TABLE `huyen`
  ADD CONSTRAINT `huyen_ibfk_1` FOREIGN KEY (`MaTinh`) REFERENCES `tinh` (`MaTinh`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD CONSTRAINT `khachhang_ibfk_1` FOREIGN KEY (`MaHuyen`) REFERENCES `huyen` (`MaHuyen`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`MaHuyen`) REFERENCES `huyen` (`MaHuyen`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD CONSTRAINT `nhacungcap_ibfk_1` FOREIGN KEY (`MaHuyen`) REFERENCES `huyen` (`MaHuyen`) ON UPDATE CASCADE;

-- Các ràng buộc cho bảng `kho`
 ALTER TABLE kho
  ADD CONSTRAINT FK_kho_thuoc FOREIGN KEY (MaThuoc) REFERENCES thuoc(MaThuoc),
  ADD CONSTRAINT FK_kho_loaithuoc FOREIGN KEY (MaLoaiThuoc) REFERENCES loaithuoc(MaLoaiThuoc),  
  ADD CONSTRAINT FK_kho_donvitinh FOREIGN KEY (MaDonViTinh) REFERENCES donvitinh(MaDonViTinh);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
