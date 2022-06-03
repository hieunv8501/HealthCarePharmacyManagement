CREATE DATABASE quanlynhathuoc;

USE quanlynhathuoc;
SET DATEFORMAT dmy;

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

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng lonhap

CREATE TABLE lonhap(
	MaLo INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	MaThuoc INT NOT NULL,
	MaPhieuNhap INT NOT NULL,
	SoLuongConLai INT NOT NULL,
	DaXoa BIT DEFAULT 0,
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng chitietphieunhap

CREATE TABLE chitietphieunhap (
	MaPhieuNhap INT NOT NULL,
	MaThuoc INT NOT NULL,
	MaDonViTinh INT,
	SoLuong INT NOT NULL,
	DonGia MONEY NOT NULL,
	NgaySanXuat DATETIME,
	NgayHetHan DATETIME,
	DaXoa BIT DEFAULT 0,
	PRIMARY KEY (MaPhieuNhap, MaThuoc)
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng phieunhap

CREATE TABLE phieunhap (
	MaPhieuNhap INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MaNhaCungCap INT NOT NULL,
	MaNhanVien INT NOT NULL,
	NgayNhap DATETIME NOT NULL,
	TongTien MONEY DEFAULT 0,
	DaXoa BIT DEFAULT 0,

)

----------------------------------------------------------
-- Cấu trúc bảng cho bảng donvitinh

CREATE TABLE donvitinh
(
	MaDonViTinh int primary key NOT NULL,
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
	MaXa int NOT NULL,
	KhachQuen BIT default 0,
	DaXoa BIT DEFAULT 0,
)
-----------------------------------------------------------
-- Cấu trúc bảng cho bảng xa

CREATE TABLE xa (
	MaXa int PRIMARY KEY NOT NULL,
	TenXa nvarchar(50) NOT NULL,
	MaHuyen int NOT NULL,
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng huyen

CREATE TABLE huyen (
	MaHuyen int PRIMARY KEY NOT NULL,
	TenHuyen nvarchar(50) NOT NULL,
	MaTinh int NOT NULL,
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng tinh

CREATE TABLE tinh (
	MaTinh int PRIMARY KEY NOT NULL,
	TenTinh nvarchar(50) NOT NULL,
)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng loaithuoc

create TABLE loaithuoc (
	MaLoaiThuoc int  primary key NOT NULL,
	TenLoaiThuoc nvarchar(100) NOT NULL,
	DaXoa BIT DEFAULT 0,

)

-----------------------------------------------------------
-- Cấu trúc bảng cho bảng nhacungcap

CREATE TABLE nhacungcap(
	MaNhaCungCap int  primary key NOT NULL,
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
	MaLoaiNhanVien int NOT NULL,
	NgaySinh datetime,
	MaXa int NOT NULL,
	SoDienThoai varchar(15),
	GioiTinh nvarchar(3),
	BangCap nvarchar(10) NOT NULL,
	Luong money default 0,
	DaXoa BIT DEFAULT 0,
)

----------------------------------------------------------
-- Cấu trúc bảng cho bảng loainhanvien

CREATE TABLE loainhanvien (
	MaLoaiNhanVien int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	TenLoaiNhanVien nvarchar(50),
	LuongCoBan money default 0,
	DaXoa BIT default 0
)

----------------------------------------------------------
-- Cấu trúc bảng cho bảng thuoc

create table thuoc(
	MaThuoc int  primary key ,
	TenThuoc nvarchar(50) NOT NULL,
	MoTa nvarchar(1000) NOT NULL,
	DoTuoi int NOT NULL,
	HinhAnh nvarchar NOT NULL,
	MaDonViTinh int NOT NULL,
	MaDonViQuiDoi int NOT NULL,
	TiLeQuiDoi int NOT NULL,
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
	MaNhanVien int NOT NULL,
	MaQuyen varchar(10) NOT NULL,
	DaXoa BIT DEFAULT 0,

)

----------------------------------------------------------
-- Cấu trúc bảng cho bảng phanquyen

CREATE TABLE phanquyen (
	MaQuyen varchar(10) PRIMARY KEY NOT NULL,
	TenQuyen nvarchar(20),
	ChiTietQuyen varchar(255) NOT NULL,
	DaXoa BIT DEFAULT 0,
) 

-- Tạo các ràng buộc cho các bảng thuoc
ALTER TABLE thuoc
	ADD CONSTRAINT FK_thuoc_nhacungcap foreign key (MaNhaCungCap) references nhacungcap(MaNhaCungCap),
	CONSTRAINT FK_thuoc_donvitinh foreign key (MaDonViTinh) references donvitinh(MaDonViTinh),
	CONSTRAINT FK_thuoc_loaithuoc foreign key (MaLoaiThuoc) references loaithuoc(MaLoaiThuoc),
	CONSTRAINT FK_thuoc_donviQuidoi foreign key (MaDonViTinh) references donvitinh(MaDonViTinh),
	
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
-- Các ràng buộc cho bảng xa
--
ALTER TABLE xa
	ADD CONSTRAINT FK_xa_huyen FOREIGN KEY (MaHuyen) REFERENCES huyen (MaHuyen);

--
-- Các ràng buộc cho bảng khachhang
--
ALTER TABLE khachhang
	ADD CONSTRAINT FK_khachhang_xa FOREIGN KEY (MaXa) REFERENCES xa (MaXa);

--
-- Các ràng buộc cho bảng nhanvien
--
ALTER TABLE nhanvien
	ADD CONSTRAINT FK_nhanvien_xa FOREIGN KEY (MaXa) REFERENCES xa (MaXa),
	CONSTRAINT FK_nhanvien_loainhanvien FOREIGN KEY (MaLoaiNhanVien) REFERENCES loainhanvien (MaLoaiNhanVien);

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
	IF(@MaKhuyenMai IS NOT NULL) BEGIN
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
	IF(@MaKhuyenMai IS NOT NULL) BEGIN
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
	INSERT INTO lonhap(MaThuoc, MaPhieuNhap, SoLuongConLai)
	SELECT MaThuoc, MaPhieuNhap, SoLuong
	FROM INSERTED
END

GO
-- Tính số lượng thuốc còn lại khi sửa chi tiết phiếu nhập
CREATE TRIGGER TG_UPDATE_CTPN ON chitietphieunhap 
FOR UPDATE
AS BEGIN

	DECLARE @SoLuongCu INT, @SoLuongMoi INT, @MaThuoc VARCHAR(10), @MaPhieuNhap INT
	SELECT  @SoLuongMoi = SoLuong, @MaThuoc = MaThuoc, @MaPhieuNhap = MaPhieuNhap
		FROM INSERTED
	SELECT  @SoLuongCu = SoLuong FROM DELETED

	UPDATE lonhap 
		SET SoLuongConLai = SoLuongConLai - @SoLuongCu + @SoLuongMoi
		WHERE MaThuoc = @MaThuoc AND MaPhieuNhap = @MaPhieuNhap
END

GO
-- Tính số lượng thuốc còn lại khi xóa chi tiết phiếu nhập 
CREATE TRIGGER TG_DELETE_CTPN ON chitietphieunhap 
FOR DELETE
AS BEGIN
	DECLARE @SoLuong INT, @MaThuoc VARCHAR(10), @MaPhieuNhap INT
	SELECT  @SoLuong = SoLuong, @MaThuoc = MaThuoc, @MaPhieuNhap = MaPhieuNhap
		FROM DELETED

	--UPDATE lonhap 
	--	SET SoLuongConLai = SoLuongConLai - @SoLuong
	--	WHERE MaThuoc = @MaThuoc AND MaPhieuNhap = @MaPhieuNhap
	DELETE lonhap WHERE MaPhieuNhap = @MaPhieuNhap AND MaThuoc = @MaThuoc
END

GO
-- Tính tổng tiền phiếu nhập khi thêm chi tiết phiếu nhập (nhập thuốc)
CREATE TRIGGER TG_INSERT_CTPN_1 ON chitietphieunhap 
FOR INSERT
AS BEGIN
	DECLARE @MaPhieuNhap INT, @MaThuoc INT, @MaDonViTinh INT, @SoLuong INT, @DonGia MONEY, @NgaySanXuat datetime, @NgayHetHan datetime
	SELECT @MaPhieuNhap = MaPhieuNhap, @MaThuoc = MaThuoc, @MaDonViTinh = MaDonViTinh, @SoLuong = SoLuong, @DonGia = DonGia, @NgaySanXuat = NgaySanXuat, @NgayHetHan = NgayHetHan FROM INSERTED
	
	UPDATE phieunhap SET TongTien = TongTien + (@SoLuong * @DonGia) WHERE MaPhieuNhap = @MaPhieuNhap

END

GO
-- Tính tổng tiền phiếu nhập khi sửa hoặc xóa chi tiết phiếu nhập (nhập thuốc)
CREATE TRIGGER TG_UPDATE_DELETE_CTPN_1 ON chitietphieunhap 
FOR UPDATE, DELETE
AS BEGIN
	DECLARE @TongTien MONEY, @MaPhieuNhap INT, @SoLuong INT, @DonGia MONEY

	SELECT @MaPhieuNhap = MaPhieuNhap FROM DELETED 

	SET @TongTien = 0

	DECLARE CUR_CTPN CURSOR FOR SELECT SoLuong, DonGia FROM chitietphieunhap where MaPhieuNhap = @MaPhieuNhap
	OPEN CUR_CTPN
	FETCH NEXT FROM CUR_CTPN INTO @SoLuong, @DonGia
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET @TongTien = @TongTien + (@SoLuong * @DonGia)
		FETCH NEXT FROM CUR_CTPN INTO @SoLuong, @DonGia
	END
	CLOSE CUR_CTPN
	DEALLOCATE CUR_CTPN

	UPDATE phieunhap SET TongTien = @TongTien WHERE MaPhieuNhap = @MaPhieuNhap
END

GO

--TRIGGER Tính lương cơ bản mỗi khi thêm mới hoặc sửa thông tin 1 nhân viên
CREATE TRIGGER TG_LuongCoBan_NhanVien ON nhanvien
FOR UPDATE, INSERT
AS
BEGIN
	DECLARE @LuongCoBan money, @MaLoaiNhanVien int
	SELECT @LuongCoBan = B.LuongCoBan, @MaLoaiNhanVien = A.MaLoaiNhanVien from inserted A JOIN loainhanvien B ON A.MaLoaiNhanVien = B.MaLoaiNhanVien
	UPDATE nhanvien
	SET Luong = Luong - Luong + @LuongCoBan
	WHERE MaLoaiNhanVien = @MaLoaiNhanVien
END
GO

--TRIGGER Tính lương cơ bản mỗi khi sửa thông tin 1 loại nhân viên
CREATE TRIGGER TG_LuongCoBan_LoaiNhanVien ON loainhanvien
FOR UPDATE
AS
BEGIN
	DECLARE @LuongCoBan money, @MaLoaiNhanVien int
	SELECT @LuongCoBan = A.LuongCoBan, @MaLoaiNhanVien = A.MaLoaiNhanVien from inserted A 
	UPDATE nhanvien
	SET Luong = Luong - Luong + @LuongCoBan
	WHERE MaLoaiNhanVien = @MaLoaiNhanVien
END
GO

---------------------------------------------------------------------------
-- Thêm dữ liệu cho bảng loainhanvien
INSERT INTO loainhanvien (TenLoaiNhanVien, LuongCoBan) VALUES (N'Nhân viên bán thuốc', 6500000)
INSERT INTO loainhanvien (TenLoaiNhanVien, LuongCoBan) VALUES (N'Nhân viên quản lý kho', 6000000)
INSERT INTO loainhanvien (TenLoaiNhanVien, LuongCoBan) VALUES (N'Bảo vệ', 5000000)

---------------------------------------------------------------------------
-- Thêm dữ liệu cho bảng nhanvien
INSERT INTO nhanvien (TenNhanVien, MaLoaiNhanVien, NgaySinh, MaXa, SoDienThoai, GioiTinh, BangCap) 
values (N'HieuNV', 1, '05/08/2001', 1, '0251643987', N'Nam', N'Cử nhân')
INSERT INTO nhanvien (TenNhanVien, MaLoaiNhanVien, NgaySinh, MaXa, SoDienThoai, GioiTinh, BangCap) 
values (N'QuyNV', 2, '11/04/2001', 1, '0365894854', N'Nam', N'Thạc sĩ')

---------------------------------------------------------------------------
-- Thêm dữ liệu cho bảng khachhang
INSERT INTO khachhang (TenKhachHang, GioiTinh, NgaySinh, SoDienThoai, MaXa) 
values (N'TinhBV', N'Nam', '07/02/2001','0251643987', 1)
INSERT INTO khachhang (TenKhachHang, GioiTinh, NgaySinh, SoDienThoai, MaXa) 
values (N'HauPP', N'Nam', '04/02/2001','0251643978', 1)

--Test TRIGGER

--INSERT INTO phieunhap (MaNhaCungCap, MaNhanVien, NgayNhap, TongTien) VALUES (1, 1, '22/04/2022', 10)

--INSERT INTO thuoc (TenThuoc, MoTa, DoTuoi, HinhAnh, MaDonViTinh, MaNhaCungCap, MaLoaiThuoc, GiaBan)
--		VALUES (N'Thuốc đau đầu', N'Thuốc trị đau đầu, nhức đầu', 20, 'A', 1, 1, 1, 20)

--INSERT INTO donvitinh (TenDonViTinh) values (N'Vỉ 5 viên')

--INSERT INTO tinh (MaTinh, TenTinh) values (1, N'An Giang')
--INSERT INTO huyen (MaHuyen, TenHuyen, MaTinh) values (1, N'Ba Vì', 1)

--INSERT INTO nhanvien (TenNhanVien, NgaySinh, MaHuyen, SoDienThoai, TrangThai, GioiTinh, BangCap, Luong) 
--	values (N'HieuNV', '05/08/2001', 1, '0251643987', 0, N'Nam', N'Cử nhân', 10000000)

--INSERT INTO khachhang (TenKhachHang, GioiTinh, NgaySinh, SoDienThoai, MaHuyen) 
--	values (N'TinhBui', N'Nam' ,'05/18/2001', '0125478963', 1)

--INSERT INTO nhacungcap (TenNhaCungCap, MaHuyen, SoDienThoai, Fax) 
--	values (N'Công ty Cổ phần SX Thuốc Thiên Ân', 1, '025155517', '12105552')

--INSERT INTO chitietphieunhap (MaPhieuNhap, MaThuoc, MaDonViTinh, SoLuong, DonGia, NgaySanXuat, NgayHetHan) 
--		VALUES (1, 3, 1, 1, 10, '01/03/2022', '20/05/2022' )

--UPDATE chitietphieunhap SET SoLuong = 10, NgaySanXuat = '01/04/2022' WHERE MaPhieuNhap = 1 AND MaThuoc = 3

--DELETE chitietphieunhap WHERE MaPhieuNhap = 1 AND MaThuoc = 3

--INSERT INTO hoadon (MaNhanVien, MaKhachHang, MaKhuyenMai, NgayLap) VALUES (1, 1, 1, '2022/04/22')

--INSERT INTO khuyenmai (MaKhuyenMai, PhanTramKhuyenMai) VALUES (1, 10)

--INSERT INTO chitiethoadon (MaHoaDon, MaThuoc, MaLo, MaDonViTinh, SoLuong, DonGia)
--		VALUES (1, 3, 4, 1, 5, 10000)

--UPDATE chitiethoadon SET SoLuong = 11 WHERE Malo = 3

--DELETE chitiethoadon 

--SELECT * FROM lonhap
--delete from chitietphieunhap
--SELECT * FROM chitiethoadon
--SELECT * FROM chitietphieunhap
--SELECT * FROM hoadon
--SELECT * FROM khuyenmai
--SELECT * FROM thuoc
--SELECT * FROM nhanvien
--SELECT * FROM taikhoan
--SELECT * FROM phanquyen

select * from phanquyen

insert into phanquyen (MaQuyen, TenQuyen, ChiTietQuyen) values
('Q1', N'Admin', 'qlBanThuoc qlNhapThuoc qlThuoc qlLoaiThuoc qlHoaDon qlKhuyenMai qlNhanVien qlKhachHang qlPhieuNhap qlNCC qlTaiKhoan qlQuyen'),
('Q2', N'Quản lý', 'xemThuoc xemLoaiThuoc xemHoaDon qlNhanVien qlKhachHang xemPhieuNhap xemNCC qlTaiKhoan qlQuyen'),
('Q3', N'Nhân viên bán thuốc', 'qlBanThuoc xemThuoc xemLoaiThuoc xemHoaDon xemNhanVien xemKhachHang'),
('Q5', N'Nhân viên nhập thuốc', 'qlNhapThuoc xemThuoc xemLoaiThuoc xemNhanVien qlPhieuNhap qlNCC'),
('Q4', N'Phụ bán thuốc', 'qlBanThuoc xemThuoc xemKhuyenMai xemKhachHang');

insert into taikhoan(TenTaiKhoan, MatKhau, MaNhanVien, MaQuyen) 
values('hieunv8501', N'$2a$08$LFRd4nOR7YRfL/JGbAAm9eD1XNwppYKF3M8nUnp3GIA7CfR39BZSq', 10, 'Q1'), --pass: Hieu123.
('tinhbui721', N'$2a$08$5FuDrRFu0rPhetwU0wGjiO2FyctrPVoBZuE8dgKbvz9E3cmVERs.C', 10, 'Q1'), --pass: Tinh123.
('haupham', N'$2a$08$BcgcgVng.5KR0zTeWg9qI.eVJ8XSYW7Az9RJ0WdOSwov2fHZILCae', 10, 'Q1'), --Hau123.
('vietquy', N'$2a$08$X1KHkU1s3wwIralBqUs49ueoFJg30cJdQblAde3S6rkk8OcU/MqZa', 10, 'Q1') --Quy123.

