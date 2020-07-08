package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ProductYearlyIncomeDTO")
public class ProductYearlyIncomeDTO {
    private Integer year;
    private Long income;
    private Long quantity;

    public ProductYearlyIncomeDTO() {

    }

    public ProductYearlyIncomeDTO(final int year, final long income, final long quantity) {
        this.year = year;
        this.income = income;
        this.quantity = quantity;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getIncome() {
        return this.income;
    }

    public void setIncome(long income) {
        this.income = income;
    }
    public long getQuantity(){
        return this.quantity;
    }
    public  void setQuantity(long quantity){
        this.quantity = quantity;
    }
}