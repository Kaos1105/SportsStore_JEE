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
import sportsstore.bo.ProductBO;
import sportsstore.dto.ProductDTO;

@Stateless
@Path("product")
public class ProductService {

    public ProductService() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(ProductDTO entity) {
        try {
            ProductBO productBO = new ProductBO();
            productBO.createProduct(entity);
        } catch (Exception e) {
            //
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, ProductDTO entity) {
        try {
            ProductBO productBO = new ProductBO();
            productBO.editProduct(entity);
        } catch (Exception e) {
            //
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ProductBO productBO = new ProductBO();
            productBO.removeProduct(id);
        } catch (Exception e) {
            //
        }
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
            List<ProductDTO> result = productBO.getAllProducts();
            return result;
        } catch (Exception e) {
            //
        }
        return null;
    }
}