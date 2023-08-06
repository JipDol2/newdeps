package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.common.util.ImageUtil;
import lotte.newdevps.domain.image.Image;
import lotte.newdevps.domain.image.ImageRepository;
import lotte.newdevps.domain.image.ImageType;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.post.PostRepository;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.image.ImageDTO;
import lotte.newdevps.dto.post.request.PostSaveDTO;
import lotte.newdevps.dto.post.request.PostUpdateDTO;
import lotte.newdevps.dto.post.response.PostDTO;
import lotte.newdevps.exception.post.PostNotFoundException;
import lotte.newdevps.exception.user.UserNotFoundException;
import lotte.newdevps.ui.auth.UserSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    /**
     * Issue
     * - Q. image 가 insert 된 후에 update 가 되는 현상
     * - A. image 를 먼저 save 한 뒤 post 를 save 하려고 했던 것이 원인
     * image 를 먼저 save 하면 post pk 의 값은 모른채로 insert 된다. 그 후에 post 를 insert 하면
     * 연관관계를 물고 있는 image 에는 post pk 를 정의해 주어야 하니 update 쿼리가 발생
     * <p>
     *     [2가지 방법 중 2번 선택]
     * => 1. post 를 먼저 save 한 뒤 영속성 컨텍스트에 저장이 되어있는 객체로 image save
     * => 2. post 객체에 cascadeType.All 속성 추가(영속성 전이를 위해)
     */
    public PostDTO save(UserSession session, PostSaveDTO postDto) {
        User user = userRepository.findById(session.getId()).get();

        List<MultipartFile> files = postDto.getImageFiles();

        List<ImageDTO> dtos = files.stream()
                .map(file -> imageService.uploadImage(file))
                .collect(Collectors.toList());
        dtos.forEach(dto -> dto.setType(ImageType.POST));

        List<Image> images = dtos.stream()
                .map(dto -> ImageDTO.toImageEntity(dto))
                .collect(Collectors.toList());

        Post post = PostSaveDTO.toEntity(user, postDto);
        images.forEach(image -> post.addImages(image));
        Post findPost = postRepository.save(post);

        PostDTO postDTO = PostDTO.toDto(findPost);
        postDTO.setImagesPath(dtos.stream()
                .map(image->image.getImagePath())
                .collect(Collectors.toList())
        );
        return postDTO;
    }

    public List<PostDTO> findByPostsAll() {
        return PostDTO.toDtoList(postRepository.findAll());
    }

    public PostDTO findByPostOne(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        return PostDTO.toDto(post);
    }

    public PostDTO updatePostOne(Long postId, PostUpdateDTO postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        post.updatePost(postDto);
        return PostDTO.toDto(post);
    }

    public void deletePostOne(Long postId) {
        postRepository.deleteById(postId);
    }
}
