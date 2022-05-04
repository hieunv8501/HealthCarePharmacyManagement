/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;

/**
 *
 * @author TinhBui
 */
public class DBConnection {
        public static void main(String[] args) {
        var serverName = "DESKTOP-9R6JGJB\\SQLEXPRESS";
        var loginName = "qlnt";
        var password = "qlnt";
        var databaseName = "quanlynhathuoc";
        var port = 1433;

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(serverName);
        ds.setUser(loginName);
        ds.setPassword(password);
        ds.setDatabaseName(databaseName);
        ds.setPortNumber(port);
        ds.setEncrypt(false);

        try (Connection connect = ds.getConnection()) {
            System.out.println("Connect to database " + connect.getCatalog() + " success");
//            Statement stmt = connect.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM loainhanvien");
//            while (rs.next()) {
//                String name = rs.getString("TenLoaiNhanVien");
//                System.out.println(name);
//            }
        } catch (Exception e) {
            System.err.println("Connect to database fail");
            e.printStackTrace();
        }
    }
}
