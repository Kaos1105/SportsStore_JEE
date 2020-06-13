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

import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import sportsstore.bo.ProductBO;
import sportsstore.dto.ErrorDTO;
import sportsstore.dto.ProductDTO;

@Stateless
@Path("products")
public class ProductService {

    public ProductService() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ProductDTO entity) {
        try {
            ProductBO productBO = new ProductBO();
            if (productBO.createProduct(entity))
                return Response.ok().build();
        } catch (Exception e) {
            //
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error creating product").build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, ProductDTO entity) {
        try {
            ProductBO productBO = new ProductBO();
            if (productBO.editProduct(id, entity))
                return Response.ok().build();
        } catch (Exception e) {
            //
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error editing product").build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            ProductBO productBO = new ProductBO();
            if (productBO.removeProduct(id))
                return Response.ok().build();
        } catch (Exception e) {
            //
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error editing product").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO find(@PathParam("id") Integer id) {
        try {
            ProductBO productBO = new ProductBO();
            return productBO.getProductById(id);
        } catch (Exception e) {
            //
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findAll() {
        try {
            ProductBO productBO = new ProductBO();
            return productBO.getAllProducts();
        } catch (Exception e) {
            //
        }
        return null;
    }
}