package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "YearlyIncomeStatisticsDTO")
public class YearlyIncomeDTO {
    private Integer year;
    private Long income;

    public YearlyIncomeDTO() {

    }

    public YearlyIncomeDTO(final int year, final long income) {
        this.year = year;
        this.income = income;
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
}