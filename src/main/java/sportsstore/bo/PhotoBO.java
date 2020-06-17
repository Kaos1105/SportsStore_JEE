package sportsstore.bo;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import sportsstore.dao.PhotoDAO;
import sportsstore.dao.ProductDAO;
import sportsstore.dto.PhotoDTO;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

public class PhotoBO {
    public PhotoDTO createPhoto(FormDataMultiPart form) throws Exception {
        ProductDAO productDAO = null;
        PhotoDAO photoDAO = null;

        FormDataBodyPart filePart = form.getField("file");
        FormDataBodyPart idForm = form.getField("id");
        Integer id = Integer.parseInt(idForm.getValue());

        InputStream inputStream = filePart.getValueAs(InputStream.class);

        // create temp File
        File uploadFile = File.createTempFile("temp", null);
        FileUtils.copyInputStreamToFile(inputStream, uploadFile);

        try {
            productDAO = new ProductDAO();
            if (productDAO.get(id).getName() == "")
                return null;
            photoDAO = new PhotoDAO();
            return photoDAO.addPhoto(uploadFile, id);
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
            photoDAO.closeConnection();
        }
    }
}