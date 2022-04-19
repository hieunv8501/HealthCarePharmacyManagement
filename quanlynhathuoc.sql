CREATE DATABASE quanlynhathuoc;

USE quanlynhathuoc;

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng hoadon

CREATE TABLE hoadon(
	MaHoaDon INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	MaNhanVien VARCHAR(10) NOT NULL,
	MaKhachHang VARCHAR(10) NOT NULL,
	MaKhuyenMai VARCHAR(10),
	NgayLap DATETIME,
	TongTien MONEY DEFAULT 0, 
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng chitiethoadon

CREATE TABLE chitiethoadon(
	MaHoaDon INT NOT NULL,
	MaThuoc VARCHAR(10) NOT NULL,
	MaDonViTinh INT NOT NULL,
	SoLuong INT NOT NULL,
	DonGia MONEY NOT NULL
	PRIMARY KEY (MaHoaDon, MaThuoc)
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng khuyenmai

CREATE TABLE khuyenmai (
	MaKhuyenMai VARCHAR(10) NOT NULL PRIMARY KEY,
	TenKhuyenMai VARCHAR(100) NOT NULL,
	DieuKienKhuyenMai MONEY DEFAULT 0,
	PhanTramKhuyenMai float NOT NULL,
	NgayBatDau DATETIME,
	NgayKetThuc DATETIME,
)
----------------------------------------------------------

-- Cấu trúc bảng cho bảng kho
CREATE TABLE kho ( 
	MaThuoc VARCHAR(10) NOT NULL PRIMARY KEY, 
	MaDonViTinh INT NOT NULL, 
	SoLuongConLai INT NOT NULL, 
	TinhTrang VARCHAR(20),
)

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng chitietphieunhap
--

CREATE TABLE chitietphieunhap (
  MaPhieuNhap INT NOT NULL,
  MaThuoc INT NOT NULL,
  MaDonViTinh INT NOT NULL,
  SoLuong INT NOT NULL,
  DonGia FLOAT NOT NULL,
  PRIMARY KEY (MaPhieuNhap, MaThuoc, MaDonViTinh)
)

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng phieunhap
--

CREATE TABLE phieunhap (
  MaPhieuNhap INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  MaNhaCungCap INT NOT NULL,
  MaNhanVien INT NOT NULL,
  NgayNhap DATETIME NOT NULL,
  TongTien FLOAT DEFAULT 0
)

----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng donvitinh
--

CREATE TABLE `donvitinh`(
  `MaDonViTinh` int(10) UNSIGNED NOT NULL,
  `TenDonViTinh` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng khachhang
--

CREATE TABLE khachhang (
  MaKhachHang int PRIMARY KEY NOT NULL,
  TenKhachHang nvarchar(50) NOT NULL,
  GioiTinh nvarchar(3) NOT NULL,
  NgaySinh datetime NOT NULL,
  SoDienThoai varchar(15) NOT NULL,
  MaHuyen int  NOT NULL,
  KhachQuen BIT default 0
)

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng huyen
--

CREATE TABLE huyen(
  MaHuyen int PRIMARY KEY NOT NULL,
  TenHuyen varchar(50) NOT NULL,
  MaTinh int NOT NULL
)

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng`tinh
--

CREATE TABLE tinh(
  MaTinh int PRIMARY KEY NOT NULL,
  TenTinh varchar(50) NOT NULL
)

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng loaithuoc
--

CREATE TABLE `loaithuoc` (
  `MaLoaiThuoc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoaiThuoc` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` archar(65535) COLLATE utf8_unicode_ci NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng nhacungcap
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
-- Cấu trúc bảng cho bảng nhanvien
--

CREATE TABLE nhanvien (
  MaNhanVien int PRIMARY KEY NOT NULL,
  TenNhanVien nvarchar(50) NOT NULL,
  NgaySinh datetime NOT NULL,
  MaHuyen int NOT NULL,
  SoDienThoai varchar(15) NOT NULL,
  TrangThai nvarchar(20),
  GioiTinh nvarchar(3) NOT NULL,
  BangCap nvarchar(10) NOT NULL
)

----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng thuoc
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
-- Cấu trúc bảng cho bảng taikhoan
--

CREATE TABLE taikhoan (
  TenTaiKhoan varchar(50) PRIMARY KEY NOT NULL,
  MatKhau varchar(100) NOT NULL,
  MaNhanVien varchar(10) NOT NULL,
  MaQuyen varchar(10) NOT NULL
)

----------------------------------------------------------

--
-- Cấu trúc bảng cho bảng phanquyen
--

CREATE TABLE phanquyen (
  MaQuyen varchar(10) PRIMARY KEY NOT NULL,
  TenQuyen varchar(20),
  ChiTietQuyen varchar(255) NOT NULL
) 

-- Tạo các ràng buộc cho các bảng

-- Các ràng buộc cho bảng chitietphieunhap
--
ALTER TABLE chitietphieunhap
  ADD CONSTRAINT FK_CTPHIEUNHAP_THUOC FOREIGN KEY (MaThuoc) REFERENCES thuoc (MaThuoc),
  ADD CONSTRAINT FK_CTPHIEUNHAP_PHIEUNHAP FOREIGN KEY (MaPhieuNhap) REFERENCES phieunhap (MaPhieuNhap),
  ADD CONSTRAINT FK_CTPHIEUNHAP_DVT FOREIGN KEY (MaDonViTinh) REFERENCES donvitinh (MaDonViTinh)

--
-- Các ràng buộc cho bảng phieunhap
--
ALTER TABLE phieunhap
  ADD CONSTRAINT FK_PHIEUNHAP_NCC FOREIGN KEY (MaNhaCungCap) REFERENCES nhacungcap (MaNhaCungCap),
  ADD CONSTRAINT FK_PHIEUNHAP_NV FOREIGN KEY (MaNhanVien) REFERENCES nhanvien (MaNhanVien)

--
-- Các ràng buộc cho bảng thuoc
--
ALTER TABLE `thuoc`
  ADD CONSTRAINT `thuoc_ibfk_1` FOREIGN KEY (`MaLoaiThuoc`) REFERENCES `loaithuoc` (`MaLoaiThuoc`) ON UPDATE CASCADE,
  ADD CONSTRAINT `thuoc_ibfk_2` FOREIGN KEY (`MaDonViTinh`) REFERENCES `donvitinh` (`MaDonViTinh`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng taikhoan
--
ALTER TABLE taikhoan
  ADD CONSTRAINT FK_TAIKHOAN_NV FOREIGN KEY (MaNhanVien) REFERENCES nhanvien (MaNhanVien),
  ADD CONSTRAINT FK_TAIKHOAN_PQ FOREIGN KEY (MaQuyen) REFERENCES phanquyen (MaQuyen)

--
-- Các ràng buộc cho bảng huyen
--
ALTER TABLE huyen
  ADD CONSTRAINT FK_huyen_tinh FOREIGN KEY (MaTinh) REFERENCES tinh (MaTinh);

--
-- Các ràng buộc cho bảng khachhang
--
ALTER TABLE khachhang
  ADD CONSTRAINT FK_khachhang_huyen FOREIGN KEY (MaHuyen) REFERENCES huyen (MaHuyen);

--
-- Các ràng buộc cho bảng nhanvien
--
ALTER TABLE nhanvien
  ADD CONSTRAINT FK_nhanvien_huyen FOREIGN KEY (MaHuyen) REFERENCES huyen (MaHuyen);

--
-- Các ràng buộc cho bảng nhacungcap
--
ALTER TABLE `nhacungcap`
	ADD CONSTRAINT `nhacungcap_ibfk_1` FOREIGN KEY (`MaHuyen`) REFERENCES `huyen` (`MaHuyen`) ON UPDATE CASCADE;
	ADD CONSTRAINT FK_kho_donvitinh FOREIGN KEY (MaDonViTinh) REFERENCES donvitinh(MaDonViTinh);


-- Các ràng buộc cho bảng hoadon
ALTER TABLE hoadon
	ADD CONSTRAINT FK_HOADON_KH FOREIGN KEY (MaKhachHang) REFERENCES khachhang(MaKhachHang),
	ADD CONSTRAINT FK_HOADON_NV FOREIGN KEY (MaNhanVien) REFERENCES nhanvien(MaNhanVien),
	ADD CONSTRAINT FK_HOADON_KM FOREIGN KEY (MaKhuyenMai) REFERENCES khuyenmai(MaKhuyenMai);

--Các ràng buộc cho bảng chitiethoadon
ALTER TABLE chitiethoadon
	ADD CONSTRAINT FK_CTHD_HOADON FOREIGN KEY (MaHoaDon) REFERENCES hoadon(MaHoaDon),
	ADD CONSTRAINT FK_CTHD_THUOC FOREIGN KEY (MaThuoc) REFERENCES thuoc(MaThuoc),
	ADD CONSTRAINT FK_CTHD_DVT FOREIGN KEY (MaDonViTinh) REFERENCES donvitinh(MaDonViTinh);

-- Các ràng buộc cho bảng khuyenmai
ALTER TABLE khuyenmai
	ADD CONSTRAINT CK_PTKM CHECK (PhanTramKhuyenMai > 0 AND PhanTramKhuyenMai < 100);

-- Các ràng buộc cho bảng kho
 ALTER TABLE kho
	ADD CONSTRAINT FK_KHO_THUOC FOREIGN KEY MaThuoc REFERENCES thuoc(MaThuoc),
	ADD CONSTRAINT FK_KHO_DVT FOREIGN KEY (MaDonViTinh) REFERENCES donvitinh(MaDonViTinh),
	ADD CONSTRAINT CK_SLCL CHECK (SoLuongConLai >= 0);	
	
-- Tính số lượng thuốc còn lại khi sửa chi tiết hóa dơn
CREATE TRIGGER TG_UPDATE_CTHD ON chitiethoadon 
FOR UPDATE
AS BEGIN
	DECLARE @SoLuongCu INT, @SoLuongMoi INT, @MaThuocCu VARCHAR(10), @MaThuocMoi VARCHAR(10)
	SELECT  @SoLuongMoi = SoLuong, @MaThuocMoi = MaThuoc FROM INSERTED
	SELECT  @SoLuongCu = SoLuong, @MaThuocCu = MaThuoc FROM DELETED

	IF (@MaThuocCu != @MaThuocMoi) BEGIN
		UPDATE kho SET SoLuongConLai = SoLuongConLai + @SoLuongCu WHERE MaThuoc = @MaThuocCu
		UPDATE kho SET SoLuongConLai = SoLuongConLai - @SoLuongMoi WHERE MaThuoc = @MaThuocMoi
	END
	ELSE BEGIN
		UPDATE kho SET SoLuongConLai = SoLuongConLai + @SoLuongCu - @SoLuongMoi WHERE MaThuoc = @MaThuocCu
	END
END

-- Tính số lượng thuốc còn lại khi xóa chi tiết hóa đơn
CREATE TRIGGER TG_DELETE_CTHD ON chitiethoadon
FOR DELETE
AS BEGIN
	DECLARE @SoLuong INT, @MaThuoc VARCHAR(10)
	SELECT  @SoLuong = SoLuong, @MaThuoc = MaThuoc FROM DELETED

	UPDATE kho SET SoLuongConLai = SoLuongConLai + @SoLuong WHERE MaThuoc = @MaThuoc
END

-- Tính tổng tiền khi thêm chi tiết hóa đơn
CREATE TRIGGER TG_INSERT_CTHD ON chitiethoadon 
FOR INSERT
AS BEGIN
	DECLARE @MaHoaDon INT, @SoLuong INT, @DonGia MONEY, @MaKhuyenMai VARCHAR(10), @PhanTramKhuyenMai FLOAT, @MaThuoc VARCHAR(10)
	SELECT @MaHoaDon = MaHoaDon, @SoLuong = SoLuong, @DonGia = DonGia, @MaThuoc = MaThuoc FROM INSERTED
	SELECT @MaKhuyenMa = MaKhuyenMai FROM hoadon WHERE MaHoaDon = @MaHoaDon

	SET @PhanTramKhuyenMai = 0
	IF(@MaKhuyenMai != NULL) BEGIN
		SELECT @PhanTramKhuyenMai = PhanTramKhuyenMai FROM khuyenmai WHERE MaKhuyenMai = @MaKhuyenMai
	END 

	UPDATE hoadon SET TongTien = (TongTien +  (@SoLuong * @DonGia)) - (((TongTien +  (@SoLuong * @DonGia)) *  @PhanTramKhuyenMai) / 100)  WHERE MaHoaDon = @MaHoaDon
	
	-- Cập nhật lại số lượng thuốc còn lại trong kho
	UPDATE kho SET SoLuongConLai = SoLuongConLai - @SoLuong WHERE MaThuoc = @MaThuoc
END

-- Tính tổng tiền khi sửa, xóa chi tiết hóa đơn
CREATE TRIGGER TG_UPDATE_DELETE_CTHD ON chitiethoadon 
FOR UPDATE, DELETE
AS BEGIN
	DECLARE @TongTien MONEY, @MaHoaDon INT, @SoLuong INT, @DonGia MONEY, @MaKhuyenMai VARCHAR(10), @PhanTramKhuyenMai FLOAT

	SELECT @MaHoaDon = MaHoaDon FROM DELETED 
	SELECT @MaKhuyenMa = MaKhuyenMai FROM hoadon WHERE MaHoaDon = @MaHoaDon

	SET @PhanTramKhuyenMai = 0
	IF(@MaKhuyenMai != NULL) BEGIN
		SELECT @PhanTramKhuyenMai = PhanTramKhuyenMai FROM khuyenmai WHERE MaKhuyenMai = @MaKhuyenMai
	END 

	SET @TongTien = 0

	DECLARE CUR_CTHD CURSOR FOR SELECT SoLuong, DonGia FROM chitiethoadon where MaHoaDon = @MaHoaDon
	OPEN CUR_CTHD
	FETCH NEXT FROM CUR_CTHD INTO @SoLuong, @DonGia
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET @TongTien = @TongTien + (@SoLuong * @DonGia)
		FETCH NEXT FROM CUR_CTHD INTO @SoLuong, @DonGia
	END
	CLOSE CUR_CTHD
	DEALLOCATE CUR_CTHD

	UPDATE hoadon SET TongTien = @TongTien - ((@TongTien * @PhanTramKhuyenMai) / 100) WHERE MaHoaDon = @MaHoaDon
END

-- Tính tổng tiền khi sửa (sửa mã khuyến mãi) hóa đơn
CREATE TRIGGER TG_INSERT_HOADON ON hoadon 
FOR UPDATE
AS BEGIN
	DECLARE @MaHoaDon INT, @MaKhuyenMai VARCHAR(10), @PhanTramKhuyenMai FLOAT
	SELECT @MaHoaDon = MaHoaDon, @MaKhuyenMai = MaKhuyenMai FROM INSERTED

	SET @PhanTramKhuyenMai = 0
	IF (@MaKhuyenMai != NULL) BEGIN
		SELECT @PhanTramKhuyenMai = PhanTramKhuyenMai FROM khuyenmai WHERE MaKhuyenMai = @MaKhuyenMai
	END 

	UPDATE hoadon SET TongTien = TongTien - ((TongTien *  @PhanTramKhuyenMai) / 100)  WHERE MaHoaDon = @MaHoaDon
END

-- Tính số lượng thuốc còn lại khi thêm chi tiết phiếu nhập (nhập thuốc)
CREATE TRIGGER TG_INSERT_CTPN ON chitietphieunhap 
FOR INSERT
AS BEGIN
	
	DECLARE @SoLuong INT, @MaThuoc VARCHAR(10), @MaDonViTinh INT
	SELECT  @SoLuong = SoLuong, @MaThuoc = MaThuoc, @MaDonViTinh = MaDonViTinh FROM INSERTED

	IF(NOT EXISTS (SELECT * FROM kho WHERE MaThuoc = @MaThuoc)) BEGIN
		INSERT INTO kho(MaThuoc, MaDonViTinh, SoLuongConLai) VALUES(@MaThuoc, @MaDonViTinh, @SoLuong)
	END
	ELSE BEGIN
		UPDATE kho SET SoLuongConLai = SoLuongConLai + @SoLuong WHERE MaThuoc = @MaThuoc
	END
END

-- Tính số lượng thuốc còn lại khi sửa chi tiết phiếu nhập
CREATE TRIGGER TG_UPDATE_CTPN ON chitietphieunhap 
FOR UPDATE
AS BEGIN
	DECLARE @SoLuongCu INT, @SoLuongMoi INT, @MaThuocCu VARCHAR(10), @MaThuocMoi VARCHAR(10)
	SELECT  @SoLuongMoi = SoLuong, @MaThuocMoi = MaThuoc FROM INSERTED
	SELECT  @SoLuongCu = SoLuong, @MaThuocCu = MaThuoc FROM DELETED

	IF (@MaThuocCu != @MaThuocMoi) BEGIN
		UPDATE kho SET SoLuongConLai = SoLuongConLai - @SoLuongCu WHERE MaThuoc = @MaThuocCu
		UPDATE kho SET SoLuongConLai = SoLuongConLai + @SoLuongMoi WHERE MaThuoc = @MaThuocMoi
	END
	ELSE BEGIN
		UPDATE kho SET SoLuongConLai = SoLuongConLai - @SoLuongCu + @SoLuongMoi WHERE MaThuoc = @MaThuocCu
	END
END

-- Tính số lượng thuốc còn lại khi xóa chi tiết phiếu nhập 
CREATE TRIGGER TG_DELETE_CTPN ON chitietphieunhap 
FOR DELETE
AS BEGIN
	DECLARE @SoLuong INT, @MaThuoc VARCHAR(10)
	SELECT  @SoLuong = SoLuong, @MaThuoc = MaThuoc FROM DELETED

	UPDATE kho SET SoLuongConLai = SoLuongConLai - @SoLuong WHERE MaThuoc = @MaThuoc
END
