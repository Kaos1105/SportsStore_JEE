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

import sportsstore.dto.ImportShipmentDTO;
import sportsstore.dto.ImportShipmentEnvelopeDTO;

public class ImportShipmentDAO extends AbstractDAO {

    public ImportShipmentDAO() throws Exception {

    }

    public ImportShipmentDAO(Connection conn) {
        super(conn);
    }

    public void writeShipmentDTO(ImportShipmentDTO ShipmentDTO, ResultSet rs) throws Exception {
        ShipmentDTO.setId(rs.getInt("id"));
        ShipmentDTO.setDeliverDate(rs.getDate("deliverDate"));
        ShipmentDTO.setImportID(rs.getInt("importID"));
        ShipmentDTO.setShipmentID(rs.getString("shipmentID"));
        ShipmentDTO.setShipmentCompany(rs.getString("shipmentCompany"));
        ShipmentDTO.setShipmentStatus(rs.getString("shipmentStatus"));
    }

    public List<ImportShipmentDTO> getAll() throws Exception {
        ArrayList<ImportShipmentDTO> ShipmentDTOList = new ArrayList<>();
        try {
            String query = "select * from ImportShipment";
            ResultSet rs = ImportShipmentDAO.super.ExecuteQuery(query, null);
            while (rs.next()) {
                ImportShipmentDTO ShipmentDTO = new ImportShipmentDTO();
                writeShipmentDTO(ShipmentDTO, rs);
                ShipmentDTOList.add(ShipmentDTO);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return ShipmentDTOList;
    }

    public ImportShipmentEnvelopeDTO getFiltered(int offset, int limit, Integer importID, Date deliverDate,
            String shipmentID, String shipmentCompany, String shipmentStatus) throws Exception {
        ImportShipmentEnvelopeDTO shipmentEnvelope = new ImportShipmentEnvelopeDTO();
        List<ImportShipmentDTO> ShipmentDTOList = new ArrayList<>();
        try {
            String query = "EXEC USP_FilterImportShipment ? , ? , ? , ? , ?";
            ResultSet rs = ImportShipmentDAO.super.ExecuteQuery(query,
                    new Object[] { importID, deliverDate, shipmentID, shipmentCompany, shipmentStatus });
            while (rs.next()) {
                ImportShipmentDTO ShipmentDTO = new ImportShipmentDTO();
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

    public ImportShipmentDTO get(Integer id) throws Exception {
        ImportShipmentDTO ShipmentDTO = new ImportShipmentDTO();
        try {
            String query = "select * from ImportShipment where id=" + id;
            ResultSet rs = ImportShipmentDAO.super.ExecuteQuery(query, null);
            if (rs.next()) {
                writeShipmentDTO(ShipmentDTO, rs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return ShipmentDTO;
    }

    public boolean create(ImportShipmentDTO input) throws Exception {
        try {
            String query = "EXEC USP_InsertImportShipment ? , ? , ? , ? , ? ";
            if (ImportShipmentDAO.super.ExecuteNonQuery(query,
                    new Object[] { input.getImportID(), input.getDeliverDate(), input.getShipmentStatus(),
                            input.getShipmentID(), input.getShipmentCompany() }) == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return false;
    }

    public boolean edit(ImportShipmentDTO input) throws Exception {
        try {
            String query = "EXEC USP_UpdateImportShipment ? , ? , ? , ? , ? , ?";
            if (ImportShipmentDAO.super.ExecuteNonQuery(query,
                    new Object[] { input.getId(), input.getImportID(), input.getDeliverDate(), input.getShipmentID(),
                            input.getShipmentCompany(), input.getShipmentStatus() }) == 1)
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
            if (ImportShipmentDAO.super.ExecuteNonQuery(query, null) == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return false;
    }
}
