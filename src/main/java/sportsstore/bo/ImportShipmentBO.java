/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.bo;

import java.sql.Date;
import java.util.List;

import sportsstore.dao.ImportShipmentDAO;

import sportsstore.dto.ImportShipmentDTO;
import sportsstore.dto.ImportShipmentEnvelopeDTO;

public class ImportShipmentBO {

    public List<ImportShipmentDTO> getAllShipment() throws Exception {
        ImportShipmentDAO importShipmentDAO = null;

        try {
            importShipmentDAO = new ImportShipmentDAO();

            List<ImportShipmentDTO> result = importShipmentDAO.getAll();

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            importShipmentDAO.closeConnection();

        }
    }

    public ImportShipmentEnvelopeDTO getFilteredShipment(int offset, int limit, Integer importID, String date,
            String shipmentID, String shipmentCompany, String shipmentStatus) throws Exception {
        ImportShipmentDAO importShipmentDAO = null;
        Date deliverDate = null;
        if (date != null)
            deliverDate = Date.valueOf(date);
        try {
            importShipmentDAO = new ImportShipmentDAO();
            ImportShipmentEnvelopeDTO result = importShipmentDAO.getFiltered(offset, limit, importID, deliverDate,
                    shipmentID, shipmentCompany, shipmentStatus);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            importShipmentDAO.closeConnection();
        }
    }

    public ImportShipmentDTO getShipmentById(Integer id) throws Exception {
        ImportShipmentDAO importShipmentDAO = null;

        try {
            importShipmentDAO = new ImportShipmentDAO();
            ImportShipmentDTO result = importShipmentDAO.get(id);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            importShipmentDAO.closeConnection();

        }
    }

    public boolean createShipment(ImportShipmentDTO newShipment) throws Exception {
        ImportShipmentDAO importShipmentDAO = null;
        try {
            importShipmentDAO = new ImportShipmentDAO();
            return importShipmentDAO.create(newShipment);
        } catch (Exception e) {
            throw e;
        } finally {
            importShipmentDAO.closeConnection();
        }
    }

    public boolean editShipment(Integer id, ImportShipmentDTO importShipment) throws Exception {
        ImportShipmentDAO importShipmentDAO = null;
        try {
            importShipment.setId(id);
            importShipmentDAO = new ImportShipmentDAO();
            ImportShipmentDTO result = importShipmentDAO.get(id);
            if (result.getId() == null)
                return false;
            return importShipmentDAO.edit(importShipment);
        } catch (Exception e) {
            throw e;
        } finally {
            importShipmentDAO.closeConnection();
        }
    }

    public boolean removeShipment(Integer id) throws Exception {
        ImportShipmentDAO importShipmentDAO = null;
        try {
            importShipmentDAO = new ImportShipmentDAO();
            ImportShipmentDTO result = importShipmentDAO.get(id);
            if (result.getId() == null)
                return false;
            return importShipmentDAO.remove(id);
        } catch (Exception e) {
            throw e;
        } finally {
            importShipmentDAO.closeConnection();
        }
    }
}
