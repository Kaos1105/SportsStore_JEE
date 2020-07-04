/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.bo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import sportsstore.dao.StatisticsDAO;
import sportsstore.dto.MonthlyIncomeDTO;
import sportsstore.dto.YearlyIncomeDTO;
 

/**
 *
 * @author Max
 */
public class StatisticsBO {

    // format lai date cho dung chuan yyyymmdd
    private String formatDateString(String date, Boolean isEnd) throws Exception {
        Date parsedDate = new SimpleDateFormat("MM/yyyy").parse(date);
        Format formatter = new SimpleDateFormat("yyyyMM");
        LocalDate convertedDate = parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int lastDateOfMonth = convertedDate.getMonth().length(convertedDate.isLeapYear());
        return formatter.format(parsedDate) + (isEnd ? lastDateOfMonth : "01");
    }

    public List<MonthlyIncomeDTO> getMonthlyIncomes(String dateBegin, String dateEnd) throws Exception {
        StatisticsDAO statisticsDAO = null;
        try {

            statisticsDAO = new StatisticsDAO();

            List<MonthlyIncomeDTO> result = statisticsDAO.getMonthlyIncomes(formatDateString(dateBegin, false),
                    formatDateString(dateEnd, true));

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (statisticsDAO != null)
                statisticsDAO.closeConnection();
        }
    }

    public List<YearlyIncomeDTO> getYearlyIncomes(int yearBegin, int yearEnd) throws Exception {
        StatisticsDAO statisticsDAO = null;
        try {
            // format lai date cho dung chuan
            statisticsDAO = new StatisticsDAO();

            List<YearlyIncomeDTO> result = statisticsDAO.getYearlyIncomes(yearBegin, yearEnd);

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (statisticsDAO != null)
                statisticsDAO.closeConnection();
        }
    }
}