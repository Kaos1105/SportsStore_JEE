package sportsstore.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ImportShipmentEnvelopeDTO")

public class ImportShipmentEnvelopeDTO {
    private List<ImportShipmentDTO> shipment;

    @XmlElement
    private Integer resultCount;

    public List<ImportShipmentDTO> getShipments() {
        return shipment;
    }

    public void setShipments(List<ImportShipmentDTO> shipment) {
        this.shipment = shipment;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

}