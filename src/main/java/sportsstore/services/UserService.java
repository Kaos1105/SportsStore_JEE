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
import sportsstore.bo.UserBO;
import sportsstore.dto.ProductDTO;
import sportsstore.dto.ProductEnvelopeDTO;
import sportsstore.dto.UserDTO;

@Stateless
@Path("users")
public class UserService {

    public UserService() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserDTO entity) {
        try {
            UserBO userBO = new UserBO();
            if (userBO.createUser(entity.getUserName(), entity.getEmail(), entity.getPassword()))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("{login}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserDTO entity) {
        try {
            UserBO userBO = new UserBO();
            if (userBO.checkPassAndEmail(entity.getEmail(), entity.getPassword()))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}