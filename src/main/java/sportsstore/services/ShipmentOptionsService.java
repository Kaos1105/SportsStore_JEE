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

import sportsstore.bo.ShipmentOptionsBO;
import sportsstore.dto.ShipmentOptionsDTO;

@Stateless
@Path("shipmentOptions")
public class ShipmentOptionsService {

    public ShipmentOptionsService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOptions() {
        ShipmentOptionsBO shipmentOptionsBO = new ShipmentOptionsBO();
        ShipmentOptionsDTO result = new ShipmentOptionsDTO();
        try {
            result = shipmentOptionsBO.getShipmentOptions();
            if (result.getImportFilterID() != null && result.getOrderFilterID() != null)
                return Response.ok().entity(result).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}