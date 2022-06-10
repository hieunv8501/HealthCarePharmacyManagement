/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import Controllers.ThuocController;
import Models.Thuoc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class WriteExcelThuoc {
    public static final int COLUMN_MaThuoc         = 0;
    public static final int COLUMN_TenThuoc      = 1;
    public static final int COLUMN_MoTa      = 2;
    public static final int COLUMN_DoTuoi   = 3;
    public static final int COLUMN_HinhAnh      = 4;
    public static final int COLUMN_MaDonViTinh         = 5;
    public static final int COLUMN_MaDonViQuiDoi      = 6;
    public static final int COLUMN_TiLeQuiDoi      = 7;
    public static final int COLUMN_Nhacungcap   = 8;
    public static final int COLUMN_LoaiThuoc      = 9;
    public static final int COLUMN_GiaBan      = 10;
     
    
 
    public static void writeExcel(List<Thuoc> thuocs, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
 
        // Create sheet
        Sheet sheet = workbook.createSheet("Thuocs"); // Create sheet with sheet name
 
        int rowIndex = 0;
         
        // Write header
        writeHeader(sheet, rowIndex);
 
        // Write data
        rowIndex++;
        for (Thuoc thuoc : thuocs) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeThuoc(thuoc, row);
            rowIndex++;
        }
         
        // Write footer
        //writeFooter(sheet, rowIndex);
 
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
 
        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
 
    // Create dummy data
    
 
    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(COLUMN_MaThuoc);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã Thuốc");
 
        cell = row.createCell(COLUMN_TenThuoc);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên Thuốc");
 
        cell = row.createCell(COLUMN_MoTa);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mô tả");
 
        cell = row.createCell(COLUMN_DoTuoi);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Độ tuổi");
 
        cell = row.createCell(COLUMN_HinhAnh);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình ảnh");
        
        cell = row.createCell(COLUMN_MaDonViTinh);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã Đơn vị tính");
 
        cell = row.createCell(COLUMN_MaDonViQuiDoi);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã đơn vị quy đổi");
 
        cell = row.createCell(COLUMN_TiLeQuiDoi);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tỉ lệ quy đổi");
 
        cell = row.createCell(COLUMN_Nhacungcap);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Độ tuổi");
 
        cell = row.createCell(COLUMN_LoaiThuoc);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã loại thuốc");
        
        cell = row.createCell(COLUMN_GiaBan);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giá bán lẻ");
    }
 
    // Write data
    private static void writeThuoc(Thuoc thuoc, Row row) {
        
         
        Cell cell = row.createCell(COLUMN_MaThuoc);
        cell.setCellValue(thuoc.getMaThuoc());
 
        cell = row.createCell(COLUMN_TenThuoc);
        cell.setCellValue(thuoc.getTenThuoc());
 
        cell = row.createCell(COLUMN_MoTa);
        cell.setCellValue(thuoc.getMota());
        
 
        cell = row.createCell(COLUMN_DoTuoi);
        cell.setCellValue(thuoc.getDotuoi());
         
        
        cell = row.createCell(COLUMN_HinhAnh);
        cell.setCellValue(thuoc.getHinhanh());
        cell = row.createCell(COLUMN_MaDonViTinh);
        cell.setCellValue(thuoc.getDonvitinh().getMaDonvitinh());
        cell = row.createCell(COLUMN_MaDonViQuiDoi);
        cell.setCellValue(thuoc.getDonviQuydoi().getMaDonvitinh());
        cell = row.createCell(COLUMN_TiLeQuiDoi);
        cell.setCellValue(thuoc.getTileQuydoi());
        cell = row.createCell(COLUMN_Nhacungcap);
        cell.setCellValue(thuoc.getNhacungcap().getMaNhacungcap());
        cell = row.createCell(COLUMN_LoaiThuoc);
        cell.setCellValue(thuoc.getLoaiThuoc().getMaLoaiThuoc());
        cell = row.createCell(COLUMN_GiaBan);
        cell.setCellValue(thuoc.getGiaBan());
    }
 
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
     
    // Write footer
//    private static void writeFooter(Sheet sheet, int rowIndex) {
//        // Create row
//        Row row = sheet.createRow(rowIndex);
//        Cell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//        cell.setCellFormula("SUM(E2:E6)");
//    }
     
    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
 
}