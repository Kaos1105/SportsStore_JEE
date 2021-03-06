/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.services;

import java.util.List;

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

import sportsstore.bo.UserBO;
import sportsstore.dto.UserDTO;
import sportsstore.helper.Authentication.AuthFilter;
import sportsstore.helper.Authentication.Role;

@Stateless
@Path("users")
public class UserService {

    public UserService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentUser() {
        try {
            UserDTO result = AuthFilter.currentUser;
            if (result != null)
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    @GET
    @Path("/getEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            UserBO userBO = new UserBO();
            List<UserDTO> result = userBO.getAllEmployees();
            if (!result.isEmpty())
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserDTO entity) {
        try {
            UserBO userBO = new UserBO();
            UserDTO result = userBO.createUser(entity.getUserName(), entity.getEmail(), entity.getPassword(),
                    entity.getRole());
            if (result != null)
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO entity) {
        try {
            UserBO userBO = new UserBO();
            UserDTO result = userBO.checkPassAndEmail(entity.getEmail(), entity.getPassword(), entity.getToken());
            if (result != null)
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    @DELETE
    @Path("{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("email") String email) {
        try {
            UserBO userBO = new UserBO();
            if (userBO.deleteUser(email))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(UserDTO entity) {
        try {
            UserBO userBO = new UserBO();
            if (userBO.editUser(entity.getEmail(), entity.getUserName(), entity.getPassword()))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    @PUT
    @Path("{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRole(@PathParam("email") String email, @QueryParam("role") String role) {
        try {
            UserBO userBO = new UserBO();
            if (userBO.editRole(email, role)) {
                UserDTO result = userBO.getUserFromEmail(email);
                return Response.ok().entity(result).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}