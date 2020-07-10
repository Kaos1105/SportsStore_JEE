package sportsstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import sportsstore.dto.ShipmentOptionsDTO;

public class ShipmentOptionsDAO extends AbstractDAO {
    public ShipmentOptionsDAO() throws Exception {

    }

    public ShipmentOptionsDAO(Connection conn) {
        super(conn);
    }

    public ShipmentOptionsDTO get() throws Exception {
        ShipmentOptionsDTO shipmentOptionsDTO = new ShipmentOptionsDTO();
        ArrayList<Integer> importIDs = new ArrayList<>();
        ArrayList<Integer> orderIDs = new ArrayList<>();
        ;
        try

        {
            String query = "EXEC USP_ImportIDFilter";
            ResultSet rs = ShipmentOptionsDAO.super.ExecuteQuery(query, null);
            while (rs.next()) {
                importIDs.add(rs.getInt("Id"));
            }
            String query2 = "EXEC USP_OrderIDFilter";
            ResultSet rs2 = ShipmentOptionsDAO.super.ExecuteQuery(query2, null);
            while (rs2.next()) {
                orderIDs.add(rs2.getInt("Id"));
            }
            shipmentOptionsDTO.setImportFilterID(importIDs);
            shipmentOptionsDTO.setOrderFilterID(orderIDs);
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return shipmentOptionsDTO;
    }
}