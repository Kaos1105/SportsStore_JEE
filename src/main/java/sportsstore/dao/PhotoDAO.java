package sportsstore.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

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
            PhotoDAO.super.ExecuteNonQuery(query2,
                    new Object[] { photo.getId(), photo.getUrl(), photo.isMain(), photo.getProductId() });
            return photo;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}