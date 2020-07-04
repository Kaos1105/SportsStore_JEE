/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.bo;

import java.sql.Date;
import java.util.List;

import sportsstore.dao.OrderShipmentDAO;

import sportsstore.dto.OrderShipmentDTO;
import sportsstore.dto.OrderShipmentEnvelopeDTO;

public class OrderShipmentBO {

    public List<OrderShipmentDTO> getAllShipment() throws Exception {
        OrderShipmentDAO orderShipmentDAO = null;

        try {
            orderShipmentDAO = new OrderShipmentDAO();

            List<OrderShipmentDTO> result = orderShipmentDAO.getAll();

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            orderShipmentDAO.closeConnection();

        }
    }

    public OrderShipmentEnvelopeDTO getFilteredShipment(int offset, int limit, Integer orderID, String date,
            String shipmentID, String shipmentCompany, String shipmentStatus) throws Exception {
        OrderShipmentDAO orderShipmentDAO = null;
        Date deliverDate = null;
        if (date != null)
            deliverDate = Date.valueOf(date);
        try {
            orderShipmentDAO = new OrderShipmentDAO();
            OrderShipmentEnvelopeDTO result = orderShipmentDAO.getFiltered(offset, limit, orderID, deliverDate,
                    shipmentID, shipmentCompany, shipmentStatus);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            orderShipmentDAO.closeConnection();
        }
    }

    public OrderShipmentDTO getShipmentById(Integer id) throws Exception {
        OrderShipmentDAO orderShipmentDAO = null;

        try {
            orderShipmentDAO = new OrderShipmentDAO();
            OrderShipmentDTO result = orderShipmentDAO.get(id);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            orderShipmentDAO.closeConnection();
        }
    }

    public boolean createShipment(OrderShipmentDTO newShipment) throws Exception {
        OrderShipmentDAO orderShipmentDAO = null;
        try {
            orderShipmentDAO = new OrderShipmentDAO();
            return orderShipmentDAO.create(newShipment);
        } catch (Exception e) {
            throw e;
        } finally {
            orderShipmentDAO.closeConnection();
        }
    }

    public boolean editShipment(Integer id, OrderShipmentDTO orderShipment) throws Exception {
        OrderShipmentDAO OrderShipmentDAO = null;
        try {
            orderShipment.setId(id);
            OrderShipmentDAO = new OrderShipmentDAO();
            OrderShipmentDTO result = OrderShipmentDAO.get(id);
            if (result.getId() == null)
                return false;
            return OrderShipmentDAO.edit(orderShipment);
        } catch (Exception e) {
            throw e;
        } finally {
            OrderShipmentDAO.closeConnection();
        }
    }

    public boolean removeShipment(Integer id) throws Exception {
        OrderShipmentDAO orderShipmentDAO = null;
        try {
            orderShipmentDAO = new OrderShipmentDAO();
            OrderShipmentDTO result = orderShipmentDAO.get(id);
            if (result.getId() == null)
                return false;
            return orderShipmentDAO.remove(id);
        } catch (Exception e) {
            throw e;
        } finally {
            orderShipmentDAO.closeConnection();
        }
    }
}
