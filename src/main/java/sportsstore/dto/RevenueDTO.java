package sportsstore.dto;

public class RevenueDTO {
    private Integer year;
    private Integer month;
    private Long totalRevenue;
    private Long totalQuantity;
    private Long productRevenue;
    private Long productQuantity;

    public RevenueDTO() {
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getProductRevenue() {
        return productRevenue;
    }

    public void setProductRevenue(Long productRevenue) {
        this.productRevenue = productRevenue;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
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

    public RevenueDTO(Integer year, Integer month, Long totalRevenue, Long totalQuantity, Long productRevenue,
            Long productQuantity) {
        this.setYear(year);
        this.setMonth(month);
        this.setTotalRevenue(totalRevenue);
        this.setTotalQuantity(totalQuantity);
    }
}