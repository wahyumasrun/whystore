/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author MA ATTAUHID
 */
public class user extends Koneksi {

    private String userName, userEmail, userPassword, userFullName; //properti yg hanya bisa dipakai di kelas ini
    private int userStatus;  //properti yg hanya bisa dipakai di kelas ini 
    private final Connection cn;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;

    public user() {
        cn = super.configDB();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public void tambahUSer() {
        query = "INSERT INTO user VALUES (?,?,MD5(?),?,?)";
        try {
            ps = cn.prepareStatement(query);
            ps.setString(1, this.userName);
            ps.setString(2, this.userEmail);
            ps.setString(3, this.userPassword);
            ps.setString(4, this.userFullName);
            ps.setInt(5, this.userStatus);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan");
        }
    }

    public void deleteData() {
        query = "DELETE FROM user WHERE userName=?";
        try {
            ps = cn.prepareStatement(query);
            ps.setString(1, this.userName);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }
    }

    public void ubahData() {
        query = "UPDATE user SET userEmail=?, userPassword=MD5(?), userFullName=?, userStatus=? WHERE userName=?";
        try {
            ps = cn.prepareStatement(query);
            ps.setString(1, userEmail);
            ps.setString(2, userPassword);
            ps.setString(3, userFullName);
            ps.setInt(4, userStatus);
            ps.setString(5, userName);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah");
        }
    }

    public ResultSet TampilUser() {
        try {
            query = "SELECT * FROM user";
            st = cn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Tampilkan");
        }
        return rs;
    }

    public void login() {
        query = "SELECT*FROM user WHERE userName = ? AND userPassword = MD5(?)";
        try {
            ps = cn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPassword);

            rs = ps.executeQuery();
            if (rs.next()) {
                Session.setUsername(rs.getString("userName"));
                Session.setEmail(rs.getString("userEmail"));
                Session.setFullname(rs.getString("userFullName"));
                Session.setStatus("Active"); //status milik sesion
                JOptionPane.showMessageDialog(null, "Anda Berhasil Login ");
            } else {
                Session.setStatus("Non Active");//status milik sesion
                JOptionPane.showMessageDialog(null, "Anda Gagal Login ");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void logOut() {
        Session.setUsername("");
        Session.setEmail("");
        Session.setFullname("");
        Session.setStatus("");

    }
}
