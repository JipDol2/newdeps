package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.image.Image;
import lotte.newdevps.domain.image.ImageRepository;
import lotte.newdevps.domain.image.ImageType;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.image.ImageDTO;
import lotte.newdevps.dto.user.request.UserProfileImageRequestDTO;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import lotte.newdevps.dto.user.response.UserFindDTO;
import lotte.newdevps.dto.user.response.UserSaveDTO;
import lotte.newdevps.dto.user.response.UserProfileImageResponseDTO;
import lotte.newdevps.exception.user.UserNotFoundException;
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

    public UserSaveDTO join(String socialType, UserSignUpDTO userDto){
        User user = userRepository.save(UserSignUpDTO.toEntity(socialType,userDto));
        return UserSaveDTO.toDto(user);
    }

    public UserProfileImageResponseDTO saveProfileImage(LoginSession session, UserProfileImageRequestDTO imageDTO){
        User user = userRepository.findById(session.getId())
                .orElseThrow(()->new UserNotFoundException());

        //user 에 이미 존재하는 image 가 있다면 삭제를 진행해야한다.
        if(user.getImage()!=null){
            imageService.removeImage(user.getImage().getImagePath());
        }

        ImageDTO uploadImage = imageService.uploadImage(imageDTO.getFile());
        uploadImage.setType(ImageType.PROFILE);

        Image saveImage = imageRepository.save(ImageDTO.toImageEntity(uploadImage));
        user.setImage(saveImage);

        return new UserProfileImageResponseDTO(saveImage.getStoredFileName());
    }

    public UserFindDTO findByUser(LoginSession session){
        User user = userRepository.findById(session.getId())
                .orElseThrow(() -> new UserNotFoundException());

        return UserFindDTO.toDto(user);
    }
}
