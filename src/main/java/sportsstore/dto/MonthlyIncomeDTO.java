package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MonthlyIncomeDTO")
public class MonthlyIncomeDTO {
    private Integer year;
    private Integer month;
    private Long income;

    public MonthlyIncomeDTO() {

    }

    public MonthlyIncomeDTO(final int year, final int month, final long income) {
        this.year = year;
        this.month = month;
        this.income = income;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getIncome() {
        return this.income;
    }

    public void setIncome(long income) {
        this.income = income;
    }
}