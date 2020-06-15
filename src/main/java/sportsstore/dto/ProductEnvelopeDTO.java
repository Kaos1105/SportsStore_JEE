package sportsstore.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ProductEnvelopeDTO")

public class ProductEnvelopeDTO {
    private List<ProductDTO> products;

    @XmlElement
    private Integer resultCount;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

}