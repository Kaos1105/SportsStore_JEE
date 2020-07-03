/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.bo;

import java.util.Date;
import java.util.List;


import sportsstore.dao.OrderShipmentDAO;


import sportsstore.dto.OrderShipmentDTO;
import sportsstore.dto.OrderShipmentEnvelopeDTO;


public class OrderShipmentBO {

    public List<OrderShipmentDTO> getAllShipment() throws Exception {
        OrderShipmentDAO OrderShipmentDAO = null;
        

        try {
            OrderShipmentDAO = new OrderShipmentDAO();
            

            List<OrderShipmentDTO> result = OrderShipmentDAO.getAll();

            // set product photos, image
           
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            OrderShipmentDAO.closeConnection();
            
        }
    }

    public OrderShipmentEnvelopeDTO getFilteredShipment(int offset, int limit, Integer orderID, Date deliverDate, String shipmentID,String shipmentCompany,String shipmentStatus
    		) throws Exception {
        OrderShipmentDAO OrderShipmentDAO = null;
        try {
            OrderShipmentDAO = new OrderShipmentDAO();
            OrderShipmentEnvelopeDTO result = OrderShipmentDAO.getFiltered(offset, limit, orderID, deliverDate, shipmentID, shipmentCompany, shipmentStatus);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            OrderShipmentDAO.closeConnection();
           
        }
    }

   

    public OrderShipmentDTO getShipmentById(Integer id) throws Exception {
        OrderShipmentDAO OrderShipmentDAO = null;
    

        try {
            OrderShipmentDAO = new OrderShipmentDAO();
            OrderShipmentDTO result = OrderShipmentDAO.get(id);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            OrderShipmentDAO.closeConnection();
           
        }
    }

    public boolean createShipment(OrderShipmentDTO newProduct) throws Exception {
        OrderShipmentDAO OrderShipmentDAO = null;
        try {
            OrderShipmentDAO = new OrderShipmentDAO();
            return OrderShipmentDAO.create(newProduct);
        } catch (Exception e) {
            throw e;
        } finally {
            OrderShipmentDAO.closeConnection();
        }
    }

    public boolean editShipment(Integer id, OrderShipmentDTO product) throws Exception {
        OrderShipmentDAO OrderShipmentDAO = null;
        try {
            product.setId(id);
            OrderShipmentDAO = new OrderShipmentDAO();
            OrderShipmentDTO result = OrderShipmentDAO.get(id);
            if (result.getId() == null)
                return false;
            return OrderShipmentDAO.edit(product);
        } catch (Exception e) {
            throw e;
        } finally {
            OrderShipmentDAO.closeConnection();
        }
    }

    public boolean removeShipment(Integer id) throws Exception {
        OrderShipmentDAO OrderShipmentDAO = null;
        try {
            OrderShipmentDAO = new OrderShipmentDAO();
            OrderShipmentDTO result = OrderShipmentDAO.get(id);
            if (result.getId() == null)
                return false;
            return OrderShipmentDAO.remove(id);
        } catch (Exception e) {
            throw e;
        } finally {
            OrderShipmentDAO.closeConnection();
        }
    }
}
