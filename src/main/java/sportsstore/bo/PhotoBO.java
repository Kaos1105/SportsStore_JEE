package sportsstore.bo;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import sportsstore.dao.PhotoDAO;
import sportsstore.dao.ProductDAO;
import sportsstore.dto.PhotoDTO;
import sportsstore.dto.ProductDTO;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

public class PhotoBO {
    public PhotoDTO createPhoto(FormDataMultiPart form, Integer id) throws Exception {
        ProductDAO productDAO = null;
        PhotoDAO photoDAO = null;

        FormDataBodyPart filePart = form.getField("File");
        // FormDataBodyPart idForm = form.getField("id");
        // Integer id = Integer.parseInt(idForm.getValue());

        InputStream inputStream = filePart.getValueAs(InputStream.class);

        // create temp File
        File uploadFile = File.createTempFile("temp", null);
        FileUtils.copyInputStreamToFile(inputStream, uploadFile);

        try {
            productDAO = new ProductDAO();
            ProductDTO product = productDAO.get(id);
            if (product.getName().isEmpty() || product.getName() == null)
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

    public boolean removePhoto(String id) throws Exception {
        PhotoDAO photoDAO = null;
        try {
            photoDAO = new PhotoDAO();
            PhotoDTO result = photoDAO.get(id);
            if (result.getId() != "")
                return photoDAO.remove(id);

        } catch (Exception e) {
            throw e;
        } finally {
            photoDAO.closeConnection();
        }
        return false;
    }

    public boolean setMainPhoto(String id) throws Exception {
        PhotoDAO photoDAO = null;
        try {
            photoDAO = new PhotoDAO();
            PhotoDTO result = photoDAO.get(id);
            if (result.getId() != "") {
                // find the current Main photo
                PhotoDTO currentMain = photoDAO.findMainFromProductId(result.getProductId());
                // set it to false
                if (photoDAO.setMainFalse(currentMain.getId())) {
                    // set target to true
                    return photoDAO.setMain(id);
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            photoDAO.closeConnection();
        }
        return false;
    }
}