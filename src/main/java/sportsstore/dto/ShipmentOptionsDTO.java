package sportsstore.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ShipmentOptionsDTO")
public class ShipmentOptionsDTO {
    private List<Integer> importFilterID;
    private List<Integer> orderFilterID;

    public List<Integer> getOrderFilterID() {
        return orderFilterID;
    }

    public List<Integer> getImportFilterID() {
        return importFilterID;
    }

    public void setImportFilterID(List<Integer> importFilterID) {
        this.importFilterID = importFilterID;
    }

    public void setOrderFilterID(List<Integer> orderFilterID) {
        this.orderFilterID = orderFilterID;
    }
}