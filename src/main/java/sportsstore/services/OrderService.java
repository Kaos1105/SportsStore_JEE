package sportsstore.services;

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

import sportsstore.bo.OrderBO;
import sportsstore.dto.OrderDTO;
import sportsstore.dto.OrderEnvelopeDTO;

@Stateless
@Path("orders")
public class OrderService {

    public OrderService() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(OrderDTO entity) {
        try {
            OrderBO orderBO = new OrderBO();
            if (orderBO.createOrder(entity))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, OrderDTO entity) {
        try {
            OrderBO orderBO = new OrderBO();

            if (orderBO.editOrder(id, entity)) {
                if (entity.getProducts() != null || !entity.getProducts().isEmpty()) {
                    if (orderBO.editOrderedProduct(id, entity))
                        return Response.ok().build();
                }
                return Response.ok().build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // @PUT
    // @Path("{id}/manage")
    // @Consumes(MediaType.APPLICATION_JSON)
    // public Response editOrderedProduct(@PathParam("id") Integer id, OrderDTO
    // entity) {
    // try {
    // OrderBO orderBO = new OrderBO();
    // if (orderBO.editOrderedProduct(id, entity))
    // return Response.ok().build();
    // } catch (Exception e) {
    // return
    // Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
    // }
    // return Response.status(Response.Status.BAD_REQUEST).build();
    // }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            OrderBO orderBO = new OrderBO();
            if (orderBO.removeOrder(id))
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
        OrderBO orderBO = new OrderBO();
        OrderDTO result = new OrderDTO();
        try {
            result = orderBO.getOrderById(id);
            if (result != null)
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }

    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response findAll() {
    // try {
    // ProductBO productBO = new ProductBO();
    // ProductEnvelopeDTO result = new ProductEnvelopeDTO();
    // result.setProducts(productBO.getAllProducts());
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
    public Response filterOrder(@QueryParam("offset") int offset, @QueryParam("limit") int limit,
            @QueryParam("name") String name, @QueryParam("address") String address, @QueryParam("phone") String phone,
            @QueryParam("date") String placementDate) {
        OrderBO orderBO = new OrderBO();
        OrderEnvelopeDTO result = new OrderEnvelopeDTO();
        try {
            result = orderBO.getFilteredOrders(offset, limit, name, address, phone, placementDate);
            if (!result.getOrders().isEmpty())
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            //
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(result).build();
    }
}