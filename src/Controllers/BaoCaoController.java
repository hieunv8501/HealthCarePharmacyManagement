/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author HauPC
 */
public class BaoCaoController {

    public BaoCaoController() {
    }
    
    public ArrayList<Integer> getNamHoaDon()
    {
        ArrayList<Integer> dsNam=new ArrayList<>();
        String sql = "exec LayNam";
       DBConnection newConnection=new DBConnection();
        try(Connection con = newConnection.getConn();
                Statement smt = con.createStatement();)
        {   
           
            ResultSet rs = smt.executeQuery(sql);
            while(rs.next())
            {
               dsNam.add(rs.getInt("Nam"));
            }
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return dsNam;
    }
   public HashMap getDoanhThuThang(int nam)
    {
        HashMap<Integer,Float> doanhthu = new HashMap<>();
        // String sql = "exec DoanhThuTheoNam "+ String.valueOf(nam);
          String sql = "EXEC DoanhThuTheoNam ?";
       DBConnection newConnection=new DBConnection();
        try(Connection con = newConnection.getConn();
                
                 PreparedStatement smt = con.prepareStatement(sql);)
            
        {   
            smt.setInt(1, nam);
            ResultSet rs =smt.executeQuery();
             
            while(rs.next())
            {
               doanhthu.put(rs.getInt("Thang"),rs.getFloat("doanhthu"));
            }
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
       
        return doanhthu;
    }

    public HashMap<Integer, Integer> getSoLuongDonThuoc(int nam) {
        HashMap<Integer,Integer> soLuong = new HashMap<>();
        // String sql = "exec DoanhThuTheoNam "+ String.valueOf(nam);
          String sql = "EXEC SoLuongDonBan ?";
       DBConnection newConnection=new DBConnection();
        try(Connection con = newConnection.getConn();
                
                 PreparedStatement smt = con.prepareStatement(sql);)
            
        {   
            smt.setInt(1, nam);
            ResultSet rs =smt.executeQuery();
             
            while(rs.next())
            {
               soLuong.put(rs.getInt("Thang"),rs.getInt("SoLuong"));
            }
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
       
        return soLuong;
    }
}
