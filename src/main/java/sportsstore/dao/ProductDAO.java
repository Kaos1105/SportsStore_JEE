/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sportsstore.dto.ProductDTO;

/**
 *
 * @author Max
 */
public class ProductDAO extends AbstractDAO{

    public ProductDAO() throws Exception {
        
    }

    public ProductDAO(Connection conn) {
        super(conn);
    }
    
    public void writeProductDTO(ProductDTO productDTO, ResultSet rs) throws Exception
    {
        productDTO.setId(Integer.parseInt(rs.getString("product_id")));
        productDTO.setName(rs.getString("product_name"));
        productDTO.setNotes(rs.getString("notes"));
    }
    
    public List<ProductDTO> getAll() throws Exception
    {
        ArrayList<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM product");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            
            ResultSet rs = stmt.executeQuery();
            while ((rs != null) && rs.next()) {
                ProductDTO productDTO =  new ProductDTO();
                writeProductDTO(productDTO, rs);
                productDTOList.add(productDTO);
            }
            stmt.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
        return productDTOList;
    }
    
    public ProductDTO get(Integer id) throws Exception 
    {
        ProductDTO productDTO = new ProductDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM product WHERE productname = ?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, id.toString());
            
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && rs.next()) {
                writeProductDTO(productDTO, rs);
            }
            stmt.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
        return productDTO;
    }
    
    public boolean create(ProductDTO input) throws Exception 
    {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO product(product_id, product_name, notes)");
            sql.append(" VALUES(?, ?, ?)");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, Integer.toString(input.getId()));
            stmt.setString(2, input.getName());
            stmt.setString(3, input.getNotes());
            
            stmt.execute();
            
            stmt.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }
    
    public boolean edit(ProductDTO input) throws Exception 
    {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE product SET product_name=?, notes=? WHERE product_id=?");    
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(3, Integer.toString(input.getId()));
            stmt.setString(1, input.getName());
            stmt.setString(2, input.getNotes());
            
            stmt.execute();
            
            stmt.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }
    
    public boolean remove(Integer id) throws Exception 
    {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM product WHERE product_id = ?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, id.toString());
            stmt.execute();
            stmt.close();
            
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }
}
