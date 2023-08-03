package lotte.newdevps.common.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

//@Getter
public class ImageUtil {

    @Value("${com.upload.path}")
    private static String uploadPath;

    public static String getUploadPath(){
        return uploadPath;
    }
}
