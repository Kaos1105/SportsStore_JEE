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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sportsstore.bo.PhotoBO;
import sportsstore.dto.PhotoDTO;
import com.sun.jersey.multipart.FormDataMultiPart;

@Stateless
@Path("photo")
public class PhotoService {

    public PhotoService() {
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadImage(/*
                                 * @FormDataParam("file") InputStream uploadedInputStream,
                                 * 
                                 * @FormDataParam("file") FormDataContentDisposition fileDetail,
                                 */FormDataMultiPart form) {
        // File uploadFile = new
        // File("C:\\Users\\troll\\Downloads\\Pictures\\batman.png");
        PhotoBO photoBO = new PhotoBO();
        PhotoDTO result = new PhotoDTO();

        // FormDataBodyPart filePart = form.getField("file");
        // FormDataBodyPart idForm = form.getField("id");
        // String id = idForm.getValue();
        // ContentDisposition headerOfFilePart = filePart.getContentDisposition();
        // String filePath = "E://uploaded/" + headerOfFilePart.getFileName();

        // InputStream fileInputStream = filePart.getValueAs(InputStream.class);
        // File uploadFile = FileUpload.uploadFile(fileInputStream, filePath);
        try {
            result = photoBO.createPhoto(form);
            if (result.getId() != "")
                return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.toString()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}