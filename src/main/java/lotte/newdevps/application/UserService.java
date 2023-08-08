package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.image.Image;
import lotte.newdevps.domain.image.ImageRepository;
import lotte.newdevps.domain.image.ImageType;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.image.ImageDTO;
import lotte.newdevps.dto.user.request.UserProfileImageDTO;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import lotte.newdevps.dto.user.response.UserDTO;
import lotte.newdevps.ui.auth.LoginSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public UserDTO join(String socialType,UserSignUpDTO userDto){
        User user = userRepository.save(UserSignUpDTO.toEntity(socialType,userDto));
        return UserDTO.toDto(user);
    }

    public String saveProfileImage(LoginSession session, UserProfileImageDTO imageDTO){
        User user = userRepository.findById(session.getId()).get();

        ImageDTO uploadImage = imageService.uploadImage(imageDTO.getFile());
        uploadImage.setType(ImageType.PROFILE);

        Image saveImage = imageRepository.save(ImageDTO.toImageEntity(uploadImage));
        user.setImage(saveImage);

        return saveImage.getImagePath();
    }

}
