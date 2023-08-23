package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.image.ImageRepository;
import lotte.newdevps.dto.image.ImageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 참고
 * - https://velog.io/@alswl689/SpringBoot-with-JPA-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8MN-4.%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%85%EB%A1%9C%EB%93%9C%EC%8D%B8%EB%84%A4%EC%9D%BC%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%82%AD%EC%A0%9C
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${com.upload.path}")
    private String uploadPath;

    private final ImageRepository imageRepository;

    public ImageDTO uploadImage(MultipartFile file){

        String today = String.valueOf(LocalDateTime.now().getYear());
        String folderPath = (uploadPath + "upload/" + today).replace("/", File.separator);
        File folder = new File(folderPath);

        if(!folder.exists()){
            folder.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        String imageName = uuid.substring(0,8) + "_" + file.getOriginalFilename();
        String saveName = folderPath + File.separator + imageName;
        Path path = Paths.get(saveName);

        try{
            file.transferTo(path);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ImageDTO.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFileName(imageName)
                .imagePath(saveName)
                .build();
    }

    public void removeImage(String imagePath){
        File file = new File(imagePath);

        if(file.exists()){
            file.delete();
        }
    }
}
