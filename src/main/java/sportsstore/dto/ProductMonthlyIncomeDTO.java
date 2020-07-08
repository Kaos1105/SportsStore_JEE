package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ProductMonthlyIncomeDTO")
public class ProductMonthlyIncomeDTO {
    private Integer year;
    private Long income;
    private Long quantity;
    private Integer month;

    public ProductMonthlyIncomeDTO() {

    }

    public ProductMonthlyIncomeDTO(final int year, final int month, final long income, final long quantity) {
        this.year = year;
        this.income = income;
        this.quantity = quantity;
        this.month = month;
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

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}