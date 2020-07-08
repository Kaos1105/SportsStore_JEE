package sportsstore.dao;

import sportsstore.dto.MonthlyIncomeDTO;
import sportsstore.dto.YearlyIncomeDTO;
import sportsstore.dto.ProductYearlyIncomeDTO;
import sportsstore.dto.StockDTO;
import sportsstore.dto.ProductMonthlyIncomeDTO;

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

    public void writeProductYearlyIncomeDTO(ProductYearlyIncomeDTO productYearlyIncomeDTO, ResultSet orderRs)
            throws Exception {
        productYearlyIncomeDTO.setYear(orderRs.getInt("YEAR"));
        productYearlyIncomeDTO.setIncome(orderRs.getLong("INCOME"));
        productYearlyIncomeDTO.setQuantity(orderRs.getLong("QUANTITY"));
    }

    public void writeProductMonthlyIncomeDTO(ProductMonthlyIncomeDTO productMonthlyIncomeDTO, ResultSet orderRs)
            throws Exception {
        productMonthlyIncomeDTO.setYear(orderRs.getInt("YEAR"));
        productMonthlyIncomeDTO.setIncome(orderRs.getLong("INCOME"));
        productMonthlyIncomeDTO.setQuantity(orderRs.getLong("QUANTITY"));
        productMonthlyIncomeDTO.setMonth(orderRs.getInt("MONTH"));
    }

    public void writeStockDTO(StockDTO stockDTO, ResultSet orderRs) throws Exception {
        stockDTO.setStock(orderRs.getLong("STOCK"));
    }

    public List<MonthlyIncomeDTO> getMonthlyIncomes(String dateBegin, String dateEnd) throws Exception {
        ArrayList<MonthlyIncomeDTO> monthlyIncomeDTOList = new ArrayList<>();
        try {
            String query = "Exec USP_MonthlyIncomeStatistic '" + dateBegin + "','" + dateEnd + "'";
            ResultSet orderRs = StatisticsDAO.super.ExecuteQuery(query, null);

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
            String query = "Exec USP_YearlyIncomeStatistic '" + yearBegin + "0101','" + yearEnd + "1231'";
            ResultSet orderRs = StatisticsDAO.super.ExecuteQuery(query, null);

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

    public List<ProductYearlyIncomeDTO> getProductYearlyIncomes(int yearBegin, int yearEnd, int productID)
            throws Exception {
        ArrayList<ProductYearlyIncomeDTO> productYearlyIncomeDTOList = new ArrayList<>();
        try {
            String query = "Exec USP_ProductYearlyIncomeStatistic '" + yearBegin + "0101','" + yearEnd + "1231',"
                    + productID;
            ResultSet orderRs = StatisticsDAO.super.ExecuteQuery(query, null);

            while (orderRs.next()) {
                ProductYearlyIncomeDTO productYearlyIncomeDTO = new ProductYearlyIncomeDTO();
                writeProductYearlyIncomeDTO(productYearlyIncomeDTO, orderRs);
                productYearlyIncomeDTOList.add(productYearlyIncomeDTO);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return productYearlyIncomeDTOList;
    }

    public List<ProductMonthlyIncomeDTO> getProductMonthlyIncomes(String dateBegin, String dateEnd, int productID)
            throws Exception {
        ArrayList<ProductMonthlyIncomeDTO> productMonthlyIncomeDTOList = new ArrayList<>();
        try {
            String query = "Exec USP_ProductMonthlyIncomeStatistic '" + dateBegin + "','" + dateEnd + "'," + productID;
            ResultSet orderRs = StatisticsDAO.super.ExecuteQuery(query, null);

            while (orderRs.next()) {
                ProductMonthlyIncomeDTO productMonthlyIncomeDTO = new ProductMonthlyIncomeDTO();
                writeProductMonthlyIncomeDTO(productMonthlyIncomeDTO, orderRs);
                productMonthlyIncomeDTOList.add(productMonthlyIncomeDTO);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return productMonthlyIncomeDTOList;
    }

    public StockDTO calculateStock(int productID) throws Exception {
        StockDTO stockDTO = new StockDTO();
        try {
            String query = "Exec USP_CALCULATESTOCK " + productID;
            ResultSet orderRs = StatisticsDAO.super.ExecuteQuery(query, null);

            while (orderRs.next()) {
                writeStockDTO(stockDTO, orderRs);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return stockDTO;
    }

}