/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.bo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import sportsstore.dao.IncomeDAO;
import sportsstore.dao.RevenueDAO;
import sportsstore.dto.IncomeDTO;
import sportsstore.dto.RevenueDTO;

/**
 *
 * @author Max
 */
public class StatisticsBO {

    public List<IncomeDTO> getIncomes(String strDateBegin, String strDateEnd, Integer productId) throws Exception {
        IncomeDAO incomeDAO = null;
        try {

            Date beginDate = null;
            Date endDate = null;
            if (productId == null)
                productId = 0;
            if (strDateBegin != null && strDateEnd != null) {
                beginDate = Date.valueOf(strDateBegin);
                endDate = Date.valueOf(strDateEnd);
            } else {
                beginDate = Date.valueOf(LocalDate.now());
                endDate = Date.valueOf(LocalDate.now());
            }
            incomeDAO = new IncomeDAO();

            List<IncomeDTO> result = incomeDAO.getIncomes(beginDate, endDate, productId);

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            incomeDAO.closeConnection();
        }
    }

    public List<RevenueDTO> getRevenues(String strDateBegin, String strDateEnd, Integer productId) throws Exception {
        RevenueDAO revenueDAO = null;
        try {

            Date beginDate = null;
            Date endDate = null;
            if (productId == null)
                productId = 0;
            if (strDateBegin != null && strDateEnd != null) {
                beginDate = Date.valueOf(strDateBegin);
                endDate = Date.valueOf(strDateEnd);
            } else {
                beginDate = Date.valueOf(LocalDate.now());
                endDate = Date.valueOf(LocalDate.now().plusMonths(12));
            }
            revenueDAO = new RevenueDAO();

            List<RevenueDTO> result = revenueDAO.getRevenues(beginDate, endDate, productId);

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            revenueDAO.closeConnection();
        }
    }
}