/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.services;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

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

import sportsstore.bo.ProductBO;
import sportsstore.dto.ProductDTO;
import sportsstore.dto.ProductEnvelopeDTO;
import sportsstore.helper.Authentication.Role;

@Stateless
@Path("products")
@PermitAll
public class ProductService {

    public ProductService() {
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ProductDTO entity) {
        try {
            ProductBO productBO = new ProductBO();
            if (productBO.createProduct(entity))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, ProductDTO entity) {
        try {
            ProductBO productBO = new ProductBO();
            if (productBO.editProduct(id, entity))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            ProductBO productBO = new ProductBO();
            if (productBO.removeProduct(id))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // @RolesAllowed({ Role.ROLE_ADMIN, Role.ROLE_EMPLOYEE })
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        ProductBO productBO = new ProductBO();
        ProductDTO result = new ProductDTO();
        try {
            result = productBO.getProductById(id);
            if (result.getName() != null)
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
    public Response filterProduct(@QueryParam("offset") int offset, @QueryParam("limit") int limit,
            @QueryParam("name") String name, @QueryParam("brand") String brand, @QueryParam("category") String category,
            @QueryParam("stock") int stock) {
        ProductBO productBO = new ProductBO();
        ProductEnvelopeDTO result = new ProductEnvelopeDTO();
        try {
            result = productBO.getFilteredProducts(offset, limit, name, brand, category, stock);
            if (!result.getProducts().isEmpty())
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            //
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(result).build();
    }
}