package sportsstore.dto;

import java.util.Date;
import java.util.List;

public class ImportDTO {
    private Integer id;
    private String wholesalerName;
    private String wholesalerAddress;
    private String wholesalerPhone;
    private Date placementDate;
    private String status;

    // Products
    private List<ImportedProductDTO> products;

    public ImportDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ImportDTO(Integer id, String wholesalerName, String wholesalerAddress, String wholesalerPhone,
            Date placementDate, List<ImportedProductDTO> products) {
        this.id = id;
        this.wholesalerName = wholesalerName;
        this.wholesalerAddress = wholesalerAddress;
        this.wholesalerPhone = wholesalerPhone;
        this.placementDate = placementDate;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWholesalerName() {
        return wholesalerName;
    }

    public void setWholesalerName(String wholesalerName) {
        this.wholesalerName = wholesalerName;
    }

    public String getWholesalerAddress() {
        return wholesalerAddress;
    }

    public void setWholesalerAddress(String wholesalerAddress) {
        this.wholesalerAddress = wholesalerAddress;
    }

    public String getWholesalerPhone() {
        return wholesalerPhone;
    }

    public void setWholesalerPhone(String wholesalerPhone) {
        this.wholesalerPhone = wholesalerPhone;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public List<ImportedProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ImportedProductDTO> products) {
        this.products = products;
    }

}
