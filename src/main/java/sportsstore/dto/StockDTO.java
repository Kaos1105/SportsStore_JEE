package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "StockDTO")
public class StockDTO {
    private Long stock;

    public StockDTO(final long stock) {
        this.stock = stock;
    }

    public StockDTO(){
        
    }

    public long getStock() {
        return this.stock;
    }

    public void setStock(final long stock) {
        this.stock = stock;
    }
}