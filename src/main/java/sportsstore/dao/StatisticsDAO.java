package sportsstore.dao;

import sportsstore.dto.MonthlyIncomeDTO;
import sportsstore.dto.YearlyIncomeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsDAO extends AbstractDAO {

    public StatisticsDAO() throws Exception {

    }

    public StatisticsDAO(Connection conn) {
        super(conn);
    }

    public void writeMonthlyIncomeDTO(MonthlyIncomeDTO monthlyIncomeDTO, ResultSet orderRs) throws Exception {
        monthlyIncomeDTO.setYear(orderRs.getInt("YEAR"));
        monthlyIncomeDTO.setMonth(orderRs.getInt("MONTH"));
        monthlyIncomeDTO.setIncome(orderRs.getLong("INCOME"));
    }

    public void writeYearlyIncomeDTO(YearlyIncomeDTO yearlyIncomeDTO, ResultSet orderRs) throws Exception {
        yearlyIncomeDTO.setYear(orderRs.getInt("YEAR"));
        yearlyIncomeDTO.setIncome(orderRs.getLong("INCOME"));
    }

    public List<MonthlyIncomeDTO> getMonthlyIncomes(String dateBegin, String dateEnd) throws Exception {
        ArrayList<MonthlyIncomeDTO> monthlyIncomeDTOList = new ArrayList<>();
        try {
            String orderQuery = "Exec USP_MonthlyIncomeStatistic '" + dateBegin + "','" + dateEnd + "'";
            ResultSet orderRs = StatisticsDAO.super.ExecuteQuery(orderQuery, null);

            while (orderRs.next()) {
                MonthlyIncomeDTO monthlyIncomeDTO = new MonthlyIncomeDTO();
                writeMonthlyIncomeDTO(monthlyIncomeDTO, orderRs);
                monthlyIncomeDTOList.add(monthlyIncomeDTO);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return monthlyIncomeDTOList;
    }

    public List<YearlyIncomeDTO> getYearlyIncomes(int yearBegin, int yearEnd) throws Exception {
        ArrayList<YearlyIncomeDTO> yearlyIncomeDTOList = new ArrayList<>();
        try {
            String orderQuery = "Exec USP_YearlyIncomeStatistic '" + yearBegin  + "0101','" + yearEnd  + "1231'";
            ResultSet orderRs = StatisticsDAO.super.ExecuteQuery(orderQuery, null);

            while (orderRs.next()) {
                YearlyIncomeDTO yearlyIncomeDTO = new YearlyIncomeDTO();
                writeYearlyIncomeDTO(yearlyIncomeDTO, orderRs);
                yearlyIncomeDTOList.add(yearlyIncomeDTO);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return yearlyIncomeDTOList;
    }
}