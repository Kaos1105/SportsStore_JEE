package sportsstore.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ShipmentEnvelopeDTO")

public class ShipmentEnvelopeDTO {
    private List<ShipmentDTO> shipment;

    @XmlElement
    private Integer resultCount;

    public List<ShipmentDTO> getShipments() {
        return shipment;
    }

    public void setShipments(List<ShipmentDTO> shipment) {
        this.shipment = shipment;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

}