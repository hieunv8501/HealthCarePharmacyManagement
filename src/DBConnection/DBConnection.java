package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBConnection {
    static int countConection = 0;
    static int countQuery = 0;
    static int countUpdate = 0;

    Connection conn = null;
    Statement stmt = null;
    ResultSet resultSet = null;

    String databaseName = null;
    String username = null;
    String password = null;
    String ipAddress = "localhost:1433";
    
    //constructor + kết nối 
    public DBConnection() {
        checkDriver();
        databaseName = "quanlynhathuoc";
        username = "qlnt";
        password = "qlnt";
        connectToDatabase();
    }

    // Kiểm tra Driver hỗ trợ kết nối có tồn tại hay không
    private void checkDriver() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "--ERROR! Driver for SQL connection not found");
        }
    }
    
    // Kết nối tới DB
    public void connectToDatabase() {
        try {
            String databaseURL = "jdbc:sqlserver://"+ ipAddress + ";databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true;";     
            conn = DriverManager.getConnection(databaseURL, username, password);
            stmt = conn.createStatement();
            countConection++;
            System.out.println("Code status " + countConection + ": SUCCESSFULLY CONNECTED TO DATABASE: '" + databaseName + "'");
            System.out.println("------------------------------------------------------------------------------------");
        } catch (SQLException ex) {
            //System.err.println("-- ERROR! Can't connect to database: '" + databaseName + "'");
            JOptionPane.showMessageDialog(null, "-- ERROR! CAN'T CONNECT TO DATABASE: '" + databaseName + "'");
            ex.getStackTrace();
            System.exit(1);
        }  
    }
    // Đóng kết nối
    public void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            System.out.println("SUCCESSFULLY DISCONNECTED TO '" + databaseName + ".\n");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! CAN'T CLOSE CONNECTION TO DATABASE: " + databaseName + "\n" + ex.getLocalizedMessage());
        }
    }
    
     // check kết nối
    public Boolean checkConnection() {
        if (conn == null || stmt == null) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Chưa thiết lập kết nối tới '" + databaseName + "'. Vui lòng đăng nhập để thiết lập kết nối!");
            return false;
        }
        return true;
    }
    
    // đăng nhập
//    public void logIn(String username, String password) {
//        this.username = username;
//        this.password = password;
//        connectToDatabase();
//    }

    // lấy dữ liệu theo truy vấn query string
    public ResultSet sqlQuery(String query) {
        if (checkConnection()) {
            try {
                resultSet = stmt.executeQuery(query);
                countQuery++;
                System.out.println(countQuery + ": Query Successfully Made: " + query);
                return resultSet;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "-- ERROR! Không thể đọc dữ liệu từ " + databaseName + "\n" + ex.getLocalizedMessage());
                return null;
            }
        }
        return null;
    }
    
    // update/delete data theo câu update
    public Boolean sqlUpdate(String qry) {
        if (checkConnection()) {
            try {
                stmt.executeUpdate(qry);
                countUpdate++;
                System.out.println(countUpdate + ": Successfully Updated! " + qry);
                return true;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "-- ERROR! Không thể ghi dữ liệu xuống " + databaseName + "\n" + ex.getLocalizedMessage());
                return false;
            }
        }
        return false;
    }
}
