package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "OrderDTO")
public class OrderDTO {
    private Integer id;
    private String recipientName;
    private String recipientAddress;
    private String recipientPhone;
    private Date placementDate;
    private String status;

    // Products
    private List<OrderedProductDTO> products;

    public OrderDTO() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDTO(final int id, final String recipientName, final String recipientAddress,
            final String recipientPhone, final Date placementDate, List<OrderedProductDTO> products) {
        this.id = id;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientPhone = recipientPhone;
        this.placementDate = placementDate;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public List<OrderedProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProductDTO> products) {
        this.products = products;
    }
}
