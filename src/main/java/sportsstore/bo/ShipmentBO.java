/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.bo;

import java.util.Date;
import java.util.List;


import sportsstore.dao.ShipmentDAO;


import sportsstore.dto.ShipmentDTO;
import sportsstore.dto.ShipmentEnvelopeDTO;


public class ShipmentBO {

    public List<ShipmentDTO> getAllShipment() throws Exception {
        ShipmentDAO ShipmentDAO = null;
        

        try {
            ShipmentDAO = new ShipmentDAO();
            

            List<ShipmentDTO> result = ShipmentDAO.getAll();

            // set product photos, image
           
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            ShipmentDAO.closeConnection();
            
        }
    }

    public ShipmentEnvelopeDTO getFilteredShipment(int offset, int limit, Integer importID, Date deliverDate, String shipmentID,String shipmentCompany,String shipmentStatus
    		) throws Exception {
        ShipmentDAO ShipmentDAO = null;
        try {
            ShipmentDAO = new ShipmentDAO();
            ShipmentEnvelopeDTO result = ShipmentDAO.getFiltered(offset, limit, importID, deliverDate, shipmentID, shipmentCompany, shipmentStatus);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            ShipmentDAO.closeConnection();
           
        }
    }

   

    public ShipmentDTO getShipmentById(Integer id) throws Exception {
        ShipmentDAO ShipmentDAO = null;
    

        try {
            ShipmentDAO = new ShipmentDAO();
            ShipmentDTO result = ShipmentDAO.get(id);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            ShipmentDAO.closeConnection();
           
        }
    }

    public boolean createShipment(ShipmentDTO newProduct) throws Exception {
        ShipmentDAO ShipmentDAO = null;
        try {
            ShipmentDAO = new ShipmentDAO();
            return ShipmentDAO.create(newProduct);
        } catch (Exception e) {
            throw e;
        } finally {
            ShipmentDAO.closeConnection();
        }
    }

    public boolean editShipment(Integer id, ShipmentDTO product) throws Exception {
        ShipmentDAO ShipmentDAO = null;
        try {
            product.setId(id);
            ShipmentDAO = new ShipmentDAO();
            ShipmentDTO result = ShipmentDAO.get(id);
            if (result.getId() == null)
                return false;
            return ShipmentDAO.edit(product);
        } catch (Exception e) {
            throw e;
        } finally {
            ShipmentDAO.closeConnection();
        }
    }

    public boolean removeShipment(Integer id) throws Exception {
        ShipmentDAO ShipmentDAO = null;
        try {
            ShipmentDAO = new ShipmentDAO();
            ShipmentDTO result = ShipmentDAO.get(id);
            if (result.getId() == null)
                return false;
            return ShipmentDAO.remove(id);
        } catch (Exception e) {
            throw e;
        } finally {
            ShipmentDAO.closeConnection();
        }
    }
}
