/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

/**
 *
 * @author HauPC
 */
import Controllers.DonvitinhController;
import Controllers.LoaiThuocController;
import Controllers.NhacungcapController;
import Controllers.XaController;
import Models.Thuoc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ReadExcelThuoc {
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
 
    public static void main(String[] args) throws IOException {
        final String excelFilePath = "thuocs.xlsx";
        final List<Thuoc> thuocs = readExcel(excelFilePath);
        for (Thuoc book : thuocs) {
            System.out.println(book);
        }
    }
 
    public static ArrayList<Thuoc> readExcel(String excelFilePath) throws IOException {
        ArrayList<Thuoc> listThuocs = new ArrayList<>();
 
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
 
        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
 
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
 
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
 
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
 
            // Read cells and set value for book object
            Thuoc thuoc = new Thuoc();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                case COLUMN_MaThuoc:
                    thuoc.setMaThuoc(new BigDecimal((double) cellValue).intValue());
                    break;
                case COLUMN_TenThuoc:
                    thuoc.setTenThuoc((String) getCellValue(cell));
                    break;
                case COLUMN_MoTa:
                    thuoc.setMota((String) getCellValue(cell));
                    break;
                case COLUMN_DoTuoi:
                    thuoc.setDotuoi((String) getCellValue(cell));
                    break;
                case COLUMN_HinhAnh:
                    thuoc.setHinhanh((String) getCellValue(cell));
                    break;
                case COLUMN_MaDonViTinh:
                    DonvitinhController donvitinhController=new DonvitinhController();
                    thuoc.setDonvitinh(donvitinhController.getDonvitinh(new BigDecimal((double) cellValue).intValue()));
                    break;
                case COLUMN_MaDonViQuiDoi:
                    DonvitinhController donvitinhController1=new DonvitinhController();
                    thuoc.setDonviQuydoi(donvitinhController1.getDonvitinh(new BigDecimal((double) cellValue).intValue()));
                    break;
                case COLUMN_TiLeQuiDoi:
                    thuoc.setTileQuydoi(new BigDecimal((double) cellValue).intValue());
                    break;
                case COLUMN_Nhacungcap:
                    NhacungcapController thuocController=new NhacungcapController();
                    thuoc.setNhacungcap(thuocController.getNhacungcap( new BigDecimal((double) cellValue).intValue()));
                    break;
                 case COLUMN_LoaiThuoc:
                     LoaiThuocController loaiThuocController=new LoaiThuocController();
                     thuoc.setLoaiThuoc(loaiThuocController.getLoaiThuoc( new BigDecimal((double) cellValue).intValue()));
                 case COLUMN_GiaBan:
                     thuoc.setGiaBan(new BigDecimal((double) cellValue).floatValue());
                default:
                    break;
                }
 
            }
            listThuocs.add(thuoc);
        }
 
        workbook.close();
        inputStream.close();
 
        return listThuocs;
    }
 
    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
}