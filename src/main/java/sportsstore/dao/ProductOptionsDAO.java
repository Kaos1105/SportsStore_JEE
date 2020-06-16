package sportsstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sportsstore.dto.ProductOptionsDTO;

public class ProductOptionsDAO extends AbstractDAO {
    public ProductOptionsDAO() throws Exception {

    }

    public ProductOptionsDAO(Connection conn) {
        super(conn);
    }

    public ProductOptionsDTO get() throws Exception {
        ProductOptionsDTO productOptionsDTO = new ProductOptionsDTO();
        ArrayList<String> categories = new ArrayList<String>() {
        };
        ArrayList<String> brands = new ArrayList<String>() {
        };
        try {
            String query = "select distinct Category from Product";
            ResultSet rs = ProductOptionsDAO.super.ExecuteQuery(query, null);
            while (rs.next()) {
                categories.add(rs.getString("Category"));
            }
            String query2 = "select distinct Brand from Product";
            ResultSet rs2 = ProductOptionsDAO.super.ExecuteQuery(query2, null);
            while (rs2.next()) {
                brands.add(rs2.getString("Brand"));
            }
            productOptionsDTO.setCategoryOptions(categories);
            productOptionsDTO.setBrandOptions(brands);
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return productOptionsDTO;
    }
}