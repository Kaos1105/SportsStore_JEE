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

import sportsstore.dto.OrderShipmentDTO;
import sportsstore.dto.OrderShipmentEnvelopeDTO;

public class OrderShipmentDAO extends AbstractDAO {

    public OrderShipmentDAO() throws Exception {

    }

    public OrderShipmentDAO(Connection conn) {
        super(conn);
    }

    public void writeShipmentDTO(OrderShipmentDTO ShipmentDTO, ResultSet rs) throws Exception {
        ShipmentDTO.setId(rs.getInt("id"));
        ShipmentDTO.setDeliverDate(rs.getDate("deliverDate"));
        ShipmentDTO.setOrderID(rs.getInt("orderID"));
        ShipmentDTO.setShipmentID(rs.getString("shipmentID"));
        ShipmentDTO.setShipmentCompany(rs.getString("shipmentCompany"));
        ShipmentDTO.setShipmentStatus(rs.getString("shipmentStatus"));
    }

    public List<OrderShipmentDTO> getAll() throws Exception {
        ArrayList<OrderShipmentDTO> ShipmentDTOList = new ArrayList<>();
        try {
            String query = "select * from OrderShipment";
            ResultSet rs = OrderShipmentDAO.super.ExecuteQuery(query, null);
            while (rs.next()) {
                OrderShipmentDTO ShipmentDTO = new OrderShipmentDTO();
                writeShipmentDTO(ShipmentDTO, rs);
                ShipmentDTOList.add(ShipmentDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return ShipmentDTOList;
    }

    public OrderShipmentEnvelopeDTO getFiltered(int offset, int limit, Integer orderID, Date deliverDate,
            String shipmentID, String shipmentCompany, String shipmentStatus) throws Exception {
        OrderShipmentEnvelopeDTO shipmentEnvelope = new OrderShipmentEnvelopeDTO();
        List<OrderShipmentDTO> ShipmentDTOList = new ArrayList<>();
        try {
            String query = "EXEC USP_FilterOrderShipment ? , ? , ? , ? , ?";
            ResultSet rs = OrderShipmentDAO.super.ExecuteQuery(query,
                    new Object[] { orderID, deliverDate, shipmentID, shipmentCompany, shipmentStatus });
            while (rs.next()) {
                OrderShipmentDTO ShipmentDTO = new OrderShipmentDTO();
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
            e.printStackTrace();
            // throw e;
        }
        return shipmentEnvelope;
    }

    public OrderShipmentDTO get(Integer id) throws Exception {
        OrderShipmentDTO ShipmentDTO = new OrderShipmentDTO();
        try {
            String query = "select * from OrderShipment where id=" + id;
            ResultSet rs = OrderShipmentDAO.super.ExecuteQuery(query, null);
            if (rs.next()) {
                writeShipmentDTO(ShipmentDTO, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return ShipmentDTO;
    }

    public boolean create(OrderShipmentDTO input) throws Exception {
        try {
            String query = "EXEC USP_InsertOrderShipment ? , ? , ? , ? , ? ";
            if (OrderShipmentDAO.super.ExecuteNonQuery(query, new Object[] { input.getOrderID(), input.getDeliverDate(),
                    input.getShipmentStatus(), input.getShipmentID(), input.getShipmentCompany() }) == 1)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean edit(OrderShipmentDTO input) throws Exception {
        try {
            String query = "EXEC USP_UpdateOrderShipment ? , ? , ? , ? , ? , ?";
            if (OrderShipmentDAO.super.ExecuteNonQuery(query,
                    new Object[] { input.getId(), input.getOrderID(), input.getDeliverDate(), input.getShipmentID(),
                            input.getShipmentCompany(), input.getShipmentStatus() }) == 1)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean remove(Integer id) throws Exception {
        try {
            String query = "Delete from OrderShipment where Id=" + id;
            if (OrderShipmentDAO.super.ExecuteNonQuery(query, null) == 1)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }
}
