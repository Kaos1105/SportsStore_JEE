/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ShipmentDTO")
public class ShipmentDTO {
    private Integer id;
    private Date deliverDate;
    private Integer importID;
    private String shipmentStatus;
    private String shipmentCompany;
    private String shipmentID;


    public ShipmentDTO() {
    }

 

    public ShipmentDTO(final Integer id,
    final Integer importID,
    final Date deliverDate,
    final String shipmentID,
    final String shipmentCompany,
    final String shipmentStatus
    ) {
        this.id = id;
        this.deliverDate = deliverDate;
        this.importID = importID;
        this.shipmentStatus = shipmentStatus;
        this.shipmentCompany= shipmentCompany;
        this.shipmentID = shipmentID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(final Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(final String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
    public  Integer getImportID() {
    	return importID;
    }
    public void setImportID(final int ImportID) {
    	this.importID=ImportID;
    }
    public String getShipmentCompany()
    {
    	return shipmentCompany;
    }
    public void setShipmentCompany(final String shipmentCompany) {
    	this.shipmentCompany= shipmentCompany;
    }
    public String getShipmentID()
    {
    	return shipmentID;
    }
    public void setShipmentID(final String shipmentID) {
    	this.shipmentID= shipmentID;
    }
    
}

