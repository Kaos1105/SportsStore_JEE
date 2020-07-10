package sportsstore.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "OrderEnvelopeDTO")
public class OrderEnvelopeDTO {
    private List<OrderDTO> orders;

    @XmlElement
    private Integer resultCount;

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setProducts(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }
}