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
            if (rs.next()) {
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
            String query = "EXEC USP_InsertProduct ? , ? , ? , ? , ?";
            ProductDAO.super.ExecuteNonQuery(query, new Object[] { input.getName(), input.getBrand(),
                    input.getCategory(), input.getPrice(), input.getImportPrice() });
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    public boolean edit(ProductDTO input) throws Exception {
        try {
            String query = "EXEC USP_UpdateProduct ? , ? , ? , ? , ? , ?";
            ProductDAO.super.ExecuteNonQuery(query, new Object[] { input.getId(), input.getName(), input.getBrand(),
                    input.getCategory(), input.getPrice(), input.getImportPrice() });
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    public boolean remove(Integer id) throws Exception {
        try {
            String query = "Delete from Product where Id=" + id;
            ProductDAO.super.ExecuteNonQuery(query, null);

            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }
}
