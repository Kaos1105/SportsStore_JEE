package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IncomeDTO")
public class IncomeDTO {
    private Integer year;
    private Integer month;
    private Long totalIncome;
    private Long totalCost;
    private Long productIncome;
    private Long productCost;

    public IncomeDTO() {

    }

    public Long getProductCost() {
        return productCost;
    }

    public void setProductCost(Long productCost) {
        this.productCost = productCost;
    }

    public Long getProductIncome() {
        return productIncome;
    }

    public void setProductIncome(Long productIncome) {
        this.productIncome = productIncome;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public IncomeDTO(final int year, final int month, final long income, final long cost, final long productIncome,
            final long productCost) {
        this.setYear(year);
        this.setMonth(month);
        this.setTotalIncome(income);
        this.setTotalCost(cost);
        this.setProductIncome(productIncome);
        this.setProductCost(productCost);
    }

}