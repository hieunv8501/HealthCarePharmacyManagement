package Components;

import Controllers.NhacungcapController;
import Models.Nhacungcap;
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
 
public class WriteExcelNhacungcap {
    public static final int COLUMN_MaNhacungcap         = 0;
    public static final int COLUMN_TenNhacungcap      = 1;
    public static final int COLUMN_Huyen      = 2;
    public static final int COLUMN_SoDienThoai   = 3;
    public static final int COLUMN_Fax      = 4;
     
    
 
    public static void writeExcel(List<Nhacungcap> nhacungcaps, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
 
        // Create sheet
        Sheet sheet = workbook.createSheet("Nhacungcaps"); // Create sheet with sheet name
 
        int rowIndex = 0;
         
        // Write header
        writeHeader(sheet, rowIndex);
 
        // Write data
        rowIndex++;
        for (Nhacungcap nhacungcap : nhacungcaps) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeNhacungcap(nhacungcap, row);
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
        Cell cell = row.createCell(COLUMN_MaNhacungcap);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã nhà cung cấp");
 
        cell = row.createCell(COLUMN_TenNhacungcap);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên nhà cung cấp");
 
        cell = row.createCell(COLUMN_Huyen);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Huyện");
 
        cell = row.createCell(COLUMN_SoDienThoai);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");
 
        cell = row.createCell(COLUMN_Fax);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Fax");
    }
 
    // Write data
    private static void writeNhacungcap(Nhacungcap nhacungcap, Row row) {
        
         
        Cell cell = row.createCell(COLUMN_MaNhacungcap);
        cell.setCellValue(nhacungcap.getMaNhacungcap());
 
        cell = row.createCell(COLUMN_TenNhacungcap);
        cell.setCellValue(nhacungcap.getTenNhacungcap());
 
        cell = row.createCell(COLUMN_Huyen);
        cell.setCellValue(nhacungcap.getXa().getMaXa());
        
 
        cell = row.createCell(COLUMN_SoDienThoai);
        cell.setCellValue(nhacungcap.getSoDienthoai());
         
        // Create cell formula
        // totalMoney = price * quantity
        cell = row.createCell(COLUMN_Fax);
         cell.setCellValue(nhacungcap.getFax());
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