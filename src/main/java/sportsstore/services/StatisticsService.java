/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.services;

import java.util.List;

/**
 * REST Web Service
 *
 * @author Max
 */

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sportsstore.bo.StatisticsBO;
import sportsstore.dto.MonthlyIncomeDTO;
import sportsstore.dto.YearlyIncomeDTO;
import sportsstore.dao.StatisticsDAO;

@Stateless
@Path("statistics")
public class StatisticsService {

    public StatisticsService() {

    }

    @GET
    @Path("monthlyincome")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMonthlyIncome(@QueryParam("begin") String begin, @QueryParam("end") String end) {
        StatisticsBO statisticsBO = new StatisticsBO();
        List<MonthlyIncomeDTO> result = null;
        try {
            result = statisticsBO.getMonthlyIncomes(begin, end);
            if (!result.isEmpty())
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(result).build();
    }

    @GET
    @Path("yearlyincome")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getYearlyIncome(@QueryParam("begin") int yearBegin, @QueryParam("end") int yearEnd) {
        StatisticsBO statisticsBO = new StatisticsBO();
        List<YearlyIncomeDTO> result = null;
        try {
            result = statisticsBO.getYearlyIncomes(yearBegin,yearEnd);
            if (!result.isEmpty())
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(result).build();
    }

}