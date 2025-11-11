/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author MA ATTAUHID
 */
public class product extends Koneksi{
    private int productId, productCategory, productPrice;
    private String productName, productDescription;
    
    private final Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;
    
    public product() {
        cnVar = configDB();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
     public ResultSet showProduct(){
        
        try {
            query = "SELECT product.productId, product.productName, category.categoryName, product.productDescription, product.productPrice FROM product JOIN category ON product.productCategory = category.categoryId";
            
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }
    
    public void tambahProduct(){
        
        try {
            query = "INSERT INTO product VALUES (?, ?, ?, ? ,?)";
            
            psVar = cnVar.prepareStatement(query);
            psVar.setInt(1, this.productId);
            psVar.setString(2, this.productName);
            psVar.setInt(3, this.productCategory);
            psVar.setString(4, this.productDescription);
            psVar.setInt(5, this.productPrice);
            psVar.executeUpdate();
            psVar.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }
    
    public void ubahProduct(){
        
        try {
            query = "UPDATE product SET productName = ?, productCategory = ?, productDescription = ?, productPrice = ? WHERE productId = ?";
            
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.productName);
            psVar.setInt(2, this.productCategory);
            psVar.setString(3, this.productDescription);
            psVar.setInt(4, this.productPrice);
            psVar.setInt(5, this.productId);
            psVar.executeUpdate();
            psVar.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }
    
    
   public void deleteProduct(){
        try {
            query = "DELETE FROM product WHERE productId = ?";
            
            psVar = cnVar.prepareStatement(query);
            psVar.setInt(1, this.productId);
            psVar.executeUpdate();
            psVar.close();
            
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
   }
   
   public ResultSet autoId(){
       try {
           query = "SELECT productId AS ID FROM product ORDER BY productId DESC LIMIT 1";
           
           stVar = cnVar.createStatement();
           rsVar = stVar.executeQuery(query);
       } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
       }
       return rsVar;
   }
    
}
    
    

