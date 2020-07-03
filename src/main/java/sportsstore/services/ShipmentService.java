/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.services;



import java.util.Date;

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

import sportsstore.bo.ShipmentBO;
import sportsstore.dto.ShipmentDTO;
import sportsstore.dto.ShipmentEnvelopeDTO;

@Stateless
@Path("shipment")
public class ShipmentService {

    public ShipmentService() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ShipmentDTO entity) {
        try {
            ShipmentBO ShipmentBO = new ShipmentBO();
            if (ShipmentBO.createShipment(entity)) {
            	System.out.println("got it");
                return Response.ok().build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, ShipmentDTO entity) {
        try {
            ShipmentBO ShipmentBO = new ShipmentBO();
            if (ShipmentBO.editShipment(id, entity))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            ShipmentBO ShipmentBO = new ShipmentBO();
            if (ShipmentBO.removeShipment(id))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        ShipmentBO ShipmentBO = new ShipmentBO();
        ShipmentDTO result = new ShipmentDTO();
        try {
            result = ShipmentBO.getShipmentById(id);
            if (result.getId() != null)
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(result).build();
    }

    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response findAll() {
    // try {
    // ShipmentBO ShipmentBO = new ShipmentBO();
    // ShipmentEnvelopeDTO result = new ShipmentEnvelopeDTO();
    // result.setProducts(ShipmentBO.getAllProducts());
    // if (!result.getProducts().isEmpty())
    // return Response.ok().entity(result).build();
    // } catch (Exception e) {
    // //
    // }
    // return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error
    // getting item product").build();
    // }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterProduct(@QueryParam("offset") int offset, @QueryParam("limit") int limit,
            @QueryParam("importID") Integer importID, @QueryParam("deliverDate") Date deliverDate, @QueryParam("shipmentID") String shipmentID,
            @QueryParam("shipmentCompany") String shipmentCompany, @QueryParam("shipmentStatus") String shipmentStatus
            ) {
        ShipmentBO ShipmentBO = new ShipmentBO();
        ShipmentEnvelopeDTO result = new ShipmentEnvelopeDTO();
        try {
            result = ShipmentBO.getFilteredShipment(offset, limit, importID, deliverDate, shipmentID, shipmentCompany, shipmentStatus);
            if (!result.getShipments().isEmpty()) {
         
                return Response.ok().entity(result).build();
            }
        } catch (Exception e) {
            //
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(result).build();
    }
}