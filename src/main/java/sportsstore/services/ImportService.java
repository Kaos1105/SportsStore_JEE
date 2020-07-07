package sportsstore.services;

import sportsstore.bo.ImportBO;
import sportsstore.dto.ImportDTO;
import sportsstore.dto.ImportEnvelopeDTO;
import sportsstore.helper.Authentication.Role;

import javax.annotation.security.RolesAllowed;
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

@Stateless
@Path("imports")
public class ImportService {

    public ImportService() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ImportDTO entity) {
        try {
            ImportBO importBO = new ImportBO();
            if (importBO.createImport(entity))
                return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, ImportDTO entity) {
        try {
            ImportBO importBO = new ImportBO();

            if (entity.getProducts() != null || !entity.getProducts().isEmpty()) {
                if (importBO.editImportedProduct(id, entity)) {
                    if (importBO.editImport(id, entity)) {
                        return Response.ok().build();
                    }
                }
            } else {
                if (importBO.editImport(id, entity)) {
                    return Response.ok().build();
                }
            }

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
            ImportBO importBO = new ImportBO();
            if (importBO.removeImport(id))
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
        ImportBO importBO = new ImportBO();
        ImportDTO result = new ImportDTO();
        try {
            result = importBO.getImportById(id);
            if (result != null)
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterImports(@QueryParam("offset") int offset, @QueryParam("limit") int limit,
            @QueryParam("name") String name, @QueryParam("address") String address, @QueryParam("phone") String phone,
            @QueryParam("date") String placementDate, @QueryParam("status") String status) {
        ImportBO importBO = new ImportBO();
        ImportEnvelopeDTO result = new ImportEnvelopeDTO();
        try {
            result = importBO.getFilteredImports(offset, limit, name, address, phone, placementDate, status);
            if (result.getImports() != null)
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            //
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}