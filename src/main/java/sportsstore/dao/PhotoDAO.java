package sportsstore.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sportsstore.dto.PhotoDTO;
import sportsstore.dto.PhotoUploadResultDTO;
import sportsstore.helper.IPhotoAccessor;
import sportsstore.helper.PhotoAccessor;

public class PhotoDAO extends AbstractDAO {
    protected static IPhotoAccessor photoAccessor;

    public PhotoDAO() throws Exception {
    }

    protected static IPhotoAccessor getPhotoAccessor() {
        if (photoAccessor == null)
            photoAccessor = new PhotoAccessor();
        return photoAccessor;
    }

    public PhotoDAO(Connection conn) {
        super(conn);
    }

    public void writePhotoDTO(PhotoDTO photoDTO, ResultSet rs) throws Exception {
        photoDTO.setId(rs.getString("id"));
        photoDTO.setUrl(rs.getString("url"));
        photoDTO.setMain(rs.getBoolean("isMain"));
        photoDTO.setProductId(rs.getString("ProductId"));
    }

    public List<PhotoDTO> getAll(String id) throws Exception {
        ArrayList<PhotoDTO> photoDTOs = new ArrayList<>();

        try {
            String query = "select * from Photo where ProductId =" + id;
            ResultSet rs = PhotoDAO.super.ExecuteQuery(query, null);
            while (rs.next()) {
                PhotoDTO photoDTO = new PhotoDTO();
                writePhotoDTO(photoDTO, rs);
                photoDTOs.add(photoDTO);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return photoDTOs;
    }

    public PhotoDTO get(String id) throws Exception {
        PhotoDTO photoDTO = new PhotoDTO();
        try {
            String query = "select * from Photo where id=N'" + id + "'";
            ResultSet rs = PhotoDAO.super.ExecuteQuery(query, null);
            if (rs.next()) {
                writePhotoDTO(photoDTO, rs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return photoDTO;
    }

    public PhotoDTO addPhoto(File file, Integer id) throws Exception {
        try {
            PhotoUploadResultDTO result = getPhotoAccessor().AddPhoto(file);
            boolean isMain = true;

            PhotoDTO photo = new PhotoDTO(result.getPublicId(), result.getUrl(), id.toString());

            // check if is there any main photo
            String query = "select * from Photo where ProductId=" + id;
            ResultSet rs = PhotoDAO.super.ExecuteQuery(query, null);

            while (rs.next()) {
                PhotoDTO photoDTO = new PhotoDTO();
                writePhotoDTO(photoDTO, rs);
                if (photoDTO.isMain())
                    isMain = false;
            }
            photo.setMain(isMain);

            // add photo to the db
            String query2 = "EXEC USP_InsertPhoto ? , ? , ? , ?";
            if (PhotoDAO.super.ExecuteNonQuery(query2,
                    new Object[] { photo.getId(), photo.getUrl(), photo.isMain(), photo.getProductId() }) == 1)
                return photo;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public boolean remove(String id) throws Exception {
        try {
            // delete from cloud
            String result = getPhotoAccessor().DeletePhoto(id);
            if (result != "fail")
            // delete from sever
            {
                String query = "Delete from Photo where id=N'" + id + "'";
                if (PhotoDAO.super.ExecuteNonQuery(query, null) == 1)
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;

        }
        return false;
    }

    public boolean setMain(String id) throws Exception {
        try {
            // set main in the database
            String query = "update Photo set IsMain=1 where id = N'" + id + "'";
            if (PhotoDAO.super.ExecuteNonQuery(query, null) == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;

        }
        return false;
    }

    public PhotoDTO findMainFromProductId(String id) throws Exception {
        PhotoDTO photoDTO = new PhotoDTO();

        try {
            String query = "  select * from Photo where ProductId =" + id + "and IsMain=1";
            ResultSet rs = PhotoDAO.super.ExecuteQuery(query, null);
            if (rs.next()) {
                writePhotoDTO(photoDTO, rs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return photoDTO;
    }

    public boolean setMainFalse(String id) throws Exception {
        try {
            // set main false in the database
            String query = "update Photo set IsMain=0 where id = N'" + id + "'";
            if (PhotoDAO.super.ExecuteNonQuery(query, null) == 1)
                return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return false;
    }
}