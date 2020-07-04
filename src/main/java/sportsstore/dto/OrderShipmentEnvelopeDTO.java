package sportsstore.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderShipmentEnvelopeDTO")

public class OrderShipmentEnvelopeDTO {
    private List<OrderShipmentDTO> orderShipment;

    @XmlElement
    private Integer resultCount;

    public List<OrderShipmentDTO> getShipments() {
        return orderShipment;
    }

    public void setShipments(List<OrderShipmentDTO> shipment) {
        this.orderShipment = shipment;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

}