/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * REST Web Service
 *
 * @author Max
 */

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import sportsstore.bo.PhotoBO;
import sportsstore.dto.PhotoDTO;
import sportsstore.helper.FileUpload;

@Stateless
@Path("photo")
public class PhotoService {

    public PhotoService() {
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadImage(@FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetails, Integer id) {
        File uploadFile = FileUpload.uploadFile(uploadedInputStream, fileDetails);
        PhotoBO photoBO = new PhotoBO();
        PhotoDTO result = new PhotoDTO();
        try {
            result = photoBO.createPhoto(uploadFile, id);
            if (result.getId() != "")
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}