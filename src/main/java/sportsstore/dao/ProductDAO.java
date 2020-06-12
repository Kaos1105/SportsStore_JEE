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
public class ProductDAO extends AbstractDAO {

    public ProductDAO() throws Exception {

    }

    public ProductDAO(Connection conn) {
        super(conn);
    }

    public void writeProductDTO(ProductDTO productDTO, ResultSet rs) throws Exception {
        productDTO.setId(rs.getInt(("id")));
        productDTO.setName(rs.getString("name"));
        productDTO.setBrand(rs.getString("brand"));
        productDTO.setCategory(rs.getString(("category")));
        productDTO.setPrice(rs.getFloat(("price")));
        productDTO.setImportPrice(rs.getFloat(("importPrice")));
    }

    public List<ProductDTO> getAll() throws Exception {
        ArrayList<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        try {
            String query = "select * from Product";
            ResultSet rs = ProductDAO.super.ExecuteQuery(query, null);
            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO();
                writeProductDTO(productDTO, rs);
                productDTOList.add(productDTO);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
        return productDTOList;
    }

    public ProductDTO get(Integer id) throws Exception {
        ProductDTO productDTO = new ProductDTO();
        try {
            String query = "select * from Product where id=" + id;
            ResultSet rs = ProductDAO.super.ExecuteQuery(query, null);
            if ((rs != null) && rs.next()) {
                writeProductDTO(productDTO, rs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
        return productDTO;
    }

    public boolean create(ProductDTO input) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO product(product_id, product_name, notes)");
            sql.append(" VALUES(?, ?, ?)");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, Integer.toString(input.getId()));
            stmt.setString(2, input.getName());

            stmt.execute();

            stmt.close();

            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    public boolean edit(ProductDTO input) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE product SET product_name=?, notes=? WHERE product_id=?");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(3, Integer.toString(input.getId()));
            stmt.setString(1, input.getName());

            stmt.execute();

            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    public boolean remove(Integer id) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM product WHERE product_id = ?");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, id.toString());
            stmt.execute();
            stmt.close();

            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }
}
