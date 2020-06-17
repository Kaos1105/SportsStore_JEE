package sportsstore.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUpload {
    public static File uploadFile(InputStream uploadedInputStream, String filePath) {

        try {
            OutputStream out = new FileOutputStream(new File(filePath));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(filePath));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File result = new File(filePath);
        return result;
    }
}