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
import sportsstore.dto.IncomeEnvelopeDTO;
import sportsstore.dto.RevenueDTO;
import sportsstore.dto.RevenueEnvelopeDTO;

/**
 *
 * @author Max
 */
public class StatisticsBO {

    public IncomeEnvelopeDTO getIncomes(int offset, int limit, String strDateBegin, String strDateEnd,
            Integer productId) throws Exception {
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
                endDate = Date.valueOf(LocalDate.now().plusMonths(12));
            }
            incomeDAO = new IncomeDAO();

            IncomeEnvelopeDTO result = incomeDAO.getIncomes(offset, limit, beginDate, endDate, productId);

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            incomeDAO.closeConnection();
        }
    }

    public RevenueEnvelopeDTO getRevenues(int offset, int limit, String strDateBegin, String strDateEnd,
            Integer productId) throws Exception {
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

            RevenueEnvelopeDTO result = revenueDAO.getRevenues(offset, limit, beginDate, endDate, productId);

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            revenueDAO.closeConnection();
        }
    }
}