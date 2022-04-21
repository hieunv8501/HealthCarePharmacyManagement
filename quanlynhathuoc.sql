CREATE DATABASE quanlynhathuoc;

USE quanlynhathuoc;

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng hoadon

CREATE TABLE hoadon(
	MaHoaDon INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	MaNhanVien INT NOT NULL,
	MaKhachHang INT NOT NULL,
	MaKhuyenMai VARCHAR(10),
	NgayLap DATETIME,
	TongTien MONEY DEFAULT 0, 
	DaXoa BIT DEFAULT 0,
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng chitiethoadon

CREATE TABLE chitiethoadon(
	MaHoaDon INT NOT NULL,
	MaThuoc INT NOT NULL,
	MaLo INT NOT NULL,
	MaDonViTinh INT NOT NULL,
	SoLuong INT NOT NULL,
	DonGia MONEY NOT NULL,
	DaXoa BIT DEFAULT 0,
	PRIMARY KEY (MaHoaDon, MaThuoc, MaLo)
)


-----------------------------------------------------------
-- Cấu trúc bảng cho bảng khuyenmai

CREATE TABLE khuyenmai (
	MaKhuyenMai VARCHAR(10) NOT NULL PRIMARY KEY,
	TenKhuyenMai VARCHAR(100),
	DieuKienKhuyenMai MONEY DEFAULT 0,
	PhanTramKhuyenMai float NOT NULL,
	NgayBatDau DATETIME,
	NgayKetThuc DATETIME,
	DaXoa BIT DEFAULT 0,

)
----------------------------------------------------------

-- Cấu trúc bảng cho bảng kho
--CREATE TABLE kho ( 
--	MaThuoc INT NOT NULL PRIMARY KEY, 
--	MaDonViTinh INT NOT NULL, 
--	TinhTrang VARCHAR(20),
--	DaXoa BIT DEFAULT 0,
--)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng lonhap

CREATE TABLE lonhap(
	MaLo INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	MaThuoc INT NOT NULL,
	MaPhieuNhap INT NOT NULL,
	SoLuongConLai INT NOT NULL,
	NgaySanXuat DATETIME,
	NgayHetHan DATETIME,
	DaXoa BIT DEFAULT 0,
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng chitietphieunhap

CREATE TABLE chitietphieunhap (
	MaPhieuNhap INT NOT NULL,
	MaThuoc INT NOT NULL,
	MaDonViTinh INT NOT NULL,
	SoLuong INT NOT NULL,
	DonGia FLOAT NOT NULL,
	NgaySanXuat DATETIME,
	NgayHetHan DATETIME,
	DaXoa BIT DEFAULT 0,
	PRIMARY KEY (MaPhieuNhap, MaThuoc, MaDonViTinh)
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng phieunhap

CREATE TABLE phieunhap (
	MaPhieuNhap INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaNhaCungCap INT NOT NULL,
	MaNhanVien INT NOT NULL,
	NgayNhap DATETIME NOT NULL,
	TongTien FLOAT DEFAULT 0,
	DaXoa BIT DEFAULT 0,

)

----------------------------------------------------------
-- Cấu trúc bảng cho bảng donvitinh

CREATE TABLE donvitinh
(
	MaDonViTinh int IDENTITY(1,1) primary key NOT NULL,
	TenDonviTinh nvarchar(50) NOT NULL,
	DaXoa BIT DEFAULT 0,

)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng khachhang

CREATE TABLE khachhang (
	MaKhachHang int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenKhachHang nvarchar(50),
	GioiTinh nvarchar(3),
	NgaySinh datetime,
	SoDienThoai varchar(15),
	MaHuyen int  NOT NULL,
	KhachQuen BIT default 0,
	DaXoa BIT DEFAULT 0,

)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng huyen

CREATE TABLE huyen(
	MaHuyen int PRIMARY KEY NOT NULL,
	TenHuyen varchar(50) NOT NULL,
	MaTinh int NOT NULL,
	DaXoa BIT DEFAULT 0,

)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng`tinh

CREATE TABLE tinh(
	MaTinh int PRIMARY KEY NOT NULL,
	TenTinh varchar(50) NOT NULL,
	DaXoa BIT DEFAULT 0,

)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng loaithuoc

create TABLE loaithuoc(
	MaLoaiThuoc int IDENTITY(1,1) primary key NOT NULL,
	TenLoaiThuoc nvarchar(100) NOT NULL,
	DaXoa BIT DEFAULT 0,

)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng nhacungcap

CREATE TABLE nhacungcap(
	MaNhaCungCap int IDENTITY(1,1) primary key NOT NULL,
	TenNhaCungCap nvarchar(50) NOT NULL,
	MaHuyen int NOT NULL,
	SoDienThoai varchar(10)  NOT NULL,
	Fax varchar(30)  DEFAULT NULL,
	DaXoa BIT DEFAULT 0,

)

----------------------------------------------------------
-- Cấu trúc bảng cho bảng nhanvien

CREATE TABLE nhanvien (
	MaNhanVien int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenNhanVien nvarchar(50),
	NgaySinh datetime,
	MaHuyen int NOT NULL,
	SoDienThoai varchar(15),
	TrangThai nvarchar(20),
	GioiTinh nvarchar(3),
	BangCap nvarchar(10) NOT NULL,
	Luong money default 0,
	DaXoa BIT DEFAULT 0,

)

----------------------------------------------------------
-- Cấu trúc bảng cho bảng thuoc

create table thuoc(
	MaThuoc int IDENTITY(1,1) primary key ,
	TenThuoc nvarchar(50) NOT NULL,
	MoTa nvarchar(1000) NOT NULL,
	DoTuoi int NOT NULL,
	HinhAnh nvarchar NOT NULL,
	MaDonViTinh int NOT NULL,
	MaNhaCungCap int NOT NULL,
	MaLoaiThuoc int,
	GiaBan MONEY NOT NULL,
	DaXoa BIT DEFAULT 0,
 
)
----------------------------------------------------------
-- Cấu trúc bảng cho bảng taikhoan

CREATE TABLE taikhoan (
	TenTaiKhoan varchar(50) PRIMARY KEY NOT NULL,
	MatKhau varchar(100) NOT NULL,
	MaNhanVien varchar(10) NOT NULL,
	MaQuyen varchar(10) NOT NULL,
	DaXoa BIT DEFAULT 0,

)

----------------------------------------------------------
-- Cấu trúc bảng cho bảng phanquyen

CREATE TABLE phanquyen (
	MaQuyen varchar(10) PRIMARY KEY NOT NULL,
	TenQuyen varchar(20),
	ChiTietQuyen varchar(255) NOT NULL,
	DaXoa BIT DEFAULT 0,
) 

-- Tạo các ràng buộc cho các bảng thuoc
ALTER TABLE thuoc
	ADD CONSTRAINT FK_thuoc_nhacungcap foreign key (MaNhaCungCap) references nhacungcap(MaNhaCungCap),
	CONSTRAINT FK_thuoc_donvitinh foreign key (MaDonViTinh) references donvitinh(MaDonViTinh),
	CONSTRAINT FK_thuoc_loaithuoc foreign key (MaLoaiThuoc) references loaithuoc(MaLoaiThuoc)
	
-- Tạo các ràng buộc cho các bảng nhacungcap 
ALTER TABLE nhacungcap
	ADD CONSTRAINT FK_nhacungcap_huyen foreign key (MaHuyen) references huyen(MaHuyen)
-- Các ràng buộc cho bảng chitietphieunhap
--
ALTER TABLE chitietphieunhap
	ADD CONSTRAINT FK_CTPHIEUNHAP_THUOC FOREIGN KEY (MaThuoc) REFERENCES thuoc (MaThuoc),
	CONSTRAINT FK_CTPHIEUNHAP_PHIEUNHAP FOREIGN KEY (MaPhieuNhap) REFERENCES phieunhap (MaPhieuNhap),
	CONSTRAINT FK_CTPHIEUNHAP_DVT FOREIGN KEY (MaDonViTinh) REFERENCES donvitinh (MaDonViTinh)

--
-- Các ràng buộc cho bảng phieunhap
--
ALTER TABLE phieunhap
	ADD CONSTRAINT FK_PHIEUNHAP_NCC FOREIGN KEY (MaNhaCungCap) REFERENCES nhacungcap (MaNhaCungCap),
	CONSTRAINT FK_PHIEUNHAP_NV FOREIGN KEY (MaNhanVien) REFERENCES nhanvien (MaNhanVien)

--

-- Các ràng buộc cho bảng taikhoan
--
ALTER TABLE taikhoan
	ADD CONSTRAINT FK_TAIKHOAN_NV FOREIGN KEY (MaNhanVien) REFERENCES nhanvien (MaNhanVien),
	CONSTRAINT FK_TAIKHOAN_PQ FOREIGN KEY (MaQuyen) REFERENCES phanquyen (MaQuyen)

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

-- Các ràng buộc cho bảng hoadon
ALTER TABLE hoadon
	ADD CONSTRAINT FK_HOADON_KH FOREIGN KEY (MaKhachHang) REFERENCES khachhang(MaKhachHang),
	CONSTRAINT FK_HOADON_NV FOREIGN KEY (MaNhanVien) REFERENCES nhanvien(MaNhanVien),
	CONSTRAINT FK_HOADON_KM FOREIGN KEY (MaKhuyenMai) REFERENCES khuyenmai(MaKhuyenMai);

--Các ràng buộc cho bảng chitiethoadon
ALTER TABLE chitiethoadon
	ADD CONSTRAINT FK_CTHD_HOADON FOREIGN KEY (MaHoaDon) REFERENCES hoadon(MaHoaDon),
	CONSTRAINT FK_CTHD_THUOC FOREIGN KEY (MaThuoc) REFERENCES thuoc(MaThuoc),
	CONSTRAINT FK_CTHD_DVT FOREIGN KEY (MaDonViTinh) REFERENCES donvitinh(MaDonViTinh),
	CONSTRAINT FK_CTHD_LONHAP FOREIGN KEY (MaLo) REFERENCES lonhap(MaLo);


-- Các ràng buộc cho bảng khuyenmai
ALTER TABLE khuyenmai
	ADD CONSTRAINT CK_PTKM CHECK (PhanTramKhuyenMai > 0 AND PhanTramKhuyenMai < 100);

-- Các ràng buộc cho bảng kho
 --ALTER TABLE kho
	--ADD CONSTRAINT FK_KHO_THUOC FOREIGN KEY (MaThuoc) REFERENCES thuoc(MaThuoc),
	--CONSTRAINT FK_KHO_DVT FOREIGN KEY (MaDonViTinh) REFERENCES donvitinh(MaDonViTinh),
	--CONSTRAINT CK_SLCL CHECK (SoLuongConLai >= 0);	

	
-- Các ràng buộc cho bảng lonhap
ALTER TABLE lonhap
	ADD CONSTRAINT FK_LONHAP_PN FOREIGN KEY (MaPhieuNhap) REFERENCES phieunhap(MaPhieuNhap),
	CONSTRAINT FK_LONHAP_THUOC FOREIGN KEY (MaThuoc) REFERENCES thuoc(MaThuoc),
	CONSTRAINT CK_SLCL CHECK (SoLuongConLai >= 0);	



GO

-- Tính số lượng thuốc còn lại khi sửa chi tiết hóa dơn
CREATE TRIGGER TG_UPDATE_CTHD ON chitiethoadon 
FOR UPDATE
AS BEGIN
	DECLARE @SoLuongCu INT, @SoLuongMoi INT, @MaLo INT
	SELECT  @SoLuongMoi = SoLuong, @MaLo = MaLo FROM INSERTED
	SELECT  @SoLuongCu = SoLuong FROM DELETED

	UPDATE lonhap SET SoLuongConLai = SoLuongConLai + @SoLuongCu - @SoLuongMoi WHERE MaLo = @MaLo
END

GO
-- Tính số lượng thuốc còn lại khi xóa chi tiết hóa đơn
CREATE TRIGGER TG_DELETE_CTHD ON chitiethoadon
FOR DELETE
AS BEGIN
	DECLARE @SoLuong INT, @MaThuoc VARCHAR(10), @MaLo INT
	SELECT  @SoLuong = SoLuong, @MaLo = MaLo FROM DELETED

	UPDATE lonhap SET SoLuongConLai = SoLuongConLai + @SoLuong WHERE MaLo = @MaLo
END

GO
-- Tính tổng tiền khi thêm chi tiết hóa đơn
CREATE TRIGGER TG_INSERT_CTHD ON chitiethoadon 
FOR INSERT
AS BEGIN
	DECLARE @MaHoaDon INT, @SoLuong INT, @DonGia MONEY, @MaKhuyenMai VARCHAR(10), @PhanTramKhuyenMai FLOAT, @MaLo INT
	SELECT @MaHoaDon = MaHoaDon, @SoLuong = SoLuong, @DonGia = DonGia,  @MaLo = MaLo FROM INSERTED
	SELECT @MaKhuyenMai = MaKhuyenMai FROM hoadon WHERE MaHoaDon = @MaHoaDon

	SET @PhanTramKhuyenMai = 0
	IF(@MaKhuyenMai != NULL) BEGIN
		SELECT @PhanTramKhuyenMai = PhanTramKhuyenMai FROM khuyenmai WHERE MaKhuyenMai = @MaKhuyenMai
	END 

	UPDATE hoadon SET TongTien = (TongTien +  (@SoLuong * @DonGia)) - (((TongTien +  (@SoLuong * @DonGia)) *  @PhanTramKhuyenMai) / 100)  WHERE MaHoaDon = @MaHoaDon
	
	-- Cập nhật lại số lượng thuốc còn lại trong lonhap
	UPDATE lonhap SET SoLuongConLai = SoLuongConLai - @SoLuong WHERE MaLo = @MaLo
END

GO
-- Tính tổng tiền khi sửa, xóa chi tiết hóa đơn
CREATE TRIGGER TG_UPDATE_DELETE_CTHD ON chitiethoadon 
FOR UPDATE, DELETE
AS BEGIN
	DECLARE @TongTien MONEY, @MaHoaDon INT, @SoLuong INT, @DonGia MONEY, @MaKhuyenMai VARCHAR(10), @PhanTramKhuyenMai FLOAT

	SELECT @MaHoaDon = MaHoaDon FROM DELETED 
	SELECT @MaKhuyenMai = MaKhuyenMai FROM hoadon WHERE MaHoaDon = @MaHoaDon

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

GO
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

GO
-- Tính số lượng thuốc còn lại khi thêm chi tiết phiếu nhập (nhập thuốc)
CREATE TRIGGER TG_INSERT_CTPN ON chitietphieunhap 
FOR INSERT
AS BEGIN
	INSERT INTO lonhap(MaThuoc, MaPhieuNhap, SoLuongConLai, NgaySanXuat, NgayHetHan)
	SELECT MaThuoc, MaPhieuNhap, SoLuong, NgaySanXuat, NgayHetHan
	FROM INSERTED
END

GO
-- Tính số lượng thuốc còn lại khi sửa chi tiết phiếu nhập
CREATE TRIGGER TG_UPDATE_CTPN ON chitietphieunhap 
FOR UPDATE
AS BEGIN

	DECLARE @SoLuongCu INT, @SoLuongMoi INT, @MaThuoc VARCHAR(10), @MaPhieuNhap INT, @NgaySanXuat DATETIME, @NgayHetHan DATETIME
	SELECT  @SoLuongMoi = SoLuong, @MaThuoc = MaThuoc, @MaPhieuNhap = MaPhieuNhap, @NgaySanXuat = NgaySanXuat, @NgayHetHan = NgayHetHan 
		FROM INSERTED
	SELECT  @SoLuongCu = SoLuong FROM DELETED

	UPDATE lonhap 
		SET SoLuongConLai = SoLuongConLai - @SoLuongCu + @SoLuongMoi, NgaySanXuat = @NgaySanXuat, NgayHetHan = @NgayHetHan 
		WHERE MaThuoc = @MaThuoc AND MaPhieuNhap = @MaPhieuNhap
END

GO
-- Tính số lượng thuốc còn lại khi xóa chi tiết phiếu nhập 
CREATE TRIGGER TG_DELETE_CTPN ON chitietphieunhap 
FOR DELETE
AS BEGIN
	DECLARE @SoLuong INT, @MaThuoc VARCHAR(10), @MaPhieuNhap INT, @NgaySanXuat DATETIME, @NgayHetHan DATETIME
	SELECT  @SoLuong = SoLuong, @MaThuoc = MaThuoc, @MaPhieuNhap = MaPhieuNhap, @NgaySanXuat = NgaySanXuat, @NgayHetHan = NgayHetHan 
		FROM DELETED

	UPDATE lonhap 
		SET SoLuongConLai = SoLuongConLai - @SoLuong, NgaySanXuat = @NgaySanXuat, NgayHetHan = @NgayHetHan 
		WHERE MaThuoc = @MaThuoc AND MaPhieuNhap = @MaPhieuNhap
END
