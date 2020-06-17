package sportsstore.bo;

import java.io.File;

import sportsstore.dao.PhotoDAO;
import sportsstore.dao.ProductDAO;
import sportsstore.dto.PhotoDTO;

public class PhotoBO {
    public PhotoDTO createPhoto(File file, Integer id) throws Exception {
        ProductDAO productDAO = null;
        PhotoDAO photoDAO = null;
        try {
            productDAO = new ProductDAO();
            if (productDAO.get(id).getName() == "")
                return null;
            photoDAO = new PhotoDAO();
            return photoDAO.addPhoto(file, id);
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
            photoDAO.closeConnection();
        }
    }
}