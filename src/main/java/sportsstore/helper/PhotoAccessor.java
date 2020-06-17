package sportsstore.helper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import sportsstore.dto.PhotoUploadResultDTO;

public class PhotoAccessor implements IPhotoAccessor {
    private static Map config = new HashMap();
    private static Cloudinary cloudinary;

    public PhotoAccessor() {
    }

    protected static Cloudinary getCloudinary() {
        if (cloudinary == null) {
            config.clear();
            config.put("cloud_name", "dufu5w88w");
            config.put("api_key", "442994854968718");
            config.put("api_secret", "g18dwRfK1JlJsvj8_Hez__fyI80");
            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }

    @Override
    public PhotoUploadResultDTO AddPhoto(File file) throws Exception {
        Map uploadResult = new HashMap();
        if ((file.length() > 0)) {
            try {
                Map params = ObjectUtils.asMap("eager",
                        Arrays.asList(new Transformation().width(500).height(500).crop("fill").gravity("face")));
                uploadResult = getCloudinary().uploader().upload(file, params);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (uploadResult.get("public_id") == null || (uploadResult.get("public_id") == ""))
            throw new Exception("Error Uploading");
        return new PhotoUploadResultDTO(uploadResult.get("public_id").toString(), uploadResult.get("url").toString());
    }

    @Override
    public String DeletePhoto(String publicId) throws Exception {
        Map deleteResult = new HashMap();
        try {
            deleteResult = getCloudinary().uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new Exception("Error Deleting");
        }
        return deleteResult.get("result") == "ok" ? "ok" : "fail";
    }

}