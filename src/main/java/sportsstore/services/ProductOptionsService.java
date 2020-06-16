/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.services;

/**
 * REST Web Service
 *
 * @author Max
 */

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sportsstore.bo.ProductBO;
import sportsstore.dto.ProductOptionsDTO;

@Stateless
@Path("productOptions")
public class ProductOptionsService {

    public ProductOptionsService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOptions() {
        ProductBO productBO = new ProductBO();
        ProductOptionsDTO result = new ProductOptionsDTO();
        try {
            result = productBO.getProductOptions();
            if (!result.getBrandOptions().isEmpty() && !result.getCategoryOptions().isEmpty())
                return Response.ok().entity(result).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}