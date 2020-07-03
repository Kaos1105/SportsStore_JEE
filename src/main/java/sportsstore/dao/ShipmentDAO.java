/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import sportsstore.dto.ShipmentDTO;
import sportsstore.dto.ShipmentEnvelopeDTO;


public class ShipmentDAO extends AbstractDAO {

    public ShipmentDAO() throws Exception {

    }

    public ShipmentDAO(Connection conn) {
        super(conn);
    }

    public void writeShipmentDTO(ShipmentDTO ShipmentDTO, ResultSet rs) throws Exception {
        ShipmentDTO.setId(rs.getInt("id"));
        ShipmentDTO.setDeliverDate(rs.getDate("deliverDate"));
        ShipmentDTO.setImportID(rs.getInt("importID"));
        ShipmentDTO.setShipmentID(rs.getString("shipmentID"));
        ShipmentDTO.setShipmentCompany(rs.getString("shipmentCompany"));
        ShipmentDTO.setShipmentStatus(rs.getString("shipmentStatus"));
    }

    public List<ShipmentDTO> getAll() throws Exception {
        ArrayList<ShipmentDTO> ShipmentDTOList = new ArrayList<>();
        try {
            String query = "select * from ImportShipment";
            ResultSet rs = ShipmentDAO.super.ExecuteQuery(query, null);
            while (rs.next()) {
                ShipmentDTO ShipmentDTO = new ShipmentDTO();
                writeShipmentDTO(ShipmentDTO, rs);
                ShipmentDTOList.add(ShipmentDTO);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return ShipmentDTOList;
    }

    public ShipmentEnvelopeDTO getFiltered(int offset, int limit,Integer importID, Date deliverDate , String shipmentID , String shipmentCompany, String shipmentStatus)
            throws Exception {
        ShipmentEnvelopeDTO shipmentEnvelope = new ShipmentEnvelopeDTO();
        List<ShipmentDTO> ShipmentDTOList = new ArrayList<>();
        try {
            String query = "EXEC USP_FilterShipment ? , ? , ? , ? , ?";
            ResultSet rs = ShipmentDAO.super.ExecuteQuery(query, new Object[] { importID, deliverDate, shipmentID, shipmentCompany,shipmentStatus });
            while (rs.next()) {
                ShipmentDTO ShipmentDTO = new ShipmentDTO();
                writeShipmentDTO(ShipmentDTO, rs);
                ShipmentDTOList.add(ShipmentDTO);
            }
            shipmentEnvelope.setResultCount(ShipmentDTOList.size());
            if (limit != 0)
                ShipmentDTOList = ShipmentDTOList.stream().skip(offset) // Equivalent to SQL's offset
                        .limit(limit) // Equivalent to SQL's limit
                        .collect(Collectors.toList());
            shipmentEnvelope.setShipments(ShipmentDTOList);
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return shipmentEnvelope;
    }

    public ShipmentDTO get(Integer id) throws Exception {
        ShipmentDTO ShipmentDTO = new ShipmentDTO();
        try {
            String query = "select * from ImportShipment where id=" + id;
            ResultSet rs = ShipmentDAO.super.ExecuteQuery(query, null);
            if (rs.next()) {
                writeShipmentDTO(ShipmentDTO, rs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return ShipmentDTO;
    }

    public boolean create(ShipmentDTO input) throws Exception {
        try {
            String query = "EXEC USP_InsertShipment ? , ? , ? , ? , ? ";
            if (ShipmentDAO.super.ExecuteNonQuery(query,
                    new Object[] { input.getImportID(), input.getDeliverDate(),input.getShipmentStatus(),  input.getShipmentID(),input.getShipmentCompany()
                             }) == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return false;
    }

    public boolean edit(ShipmentDTO input) throws Exception {
        try {
            String query = "EXEC USP_UpdateShipment ? , ? , ? , ? , ? , ?";
            if (ShipmentDAO.super.ExecuteNonQuery(query,
                    new Object[] { input.getId(),  input.getImportID(), input.getDeliverDate(), input.getShipmentStatus(), input.getShipmentID(),
                            input.getShipmentCompany() }) == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return false;
    }

    public boolean remove(Integer id) throws Exception {
        try {
            String query = "Delete from ImportShipment where Id=" + id;
            if (ShipmentDAO.super.ExecuteNonQuery(query, null) == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return false;
    }
}
