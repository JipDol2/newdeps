package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.post.PostRepository;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.post.request.PostSaveDTO;
import lotte.newdevps.dto.post.request.PostUpdateDTO;
import lotte.newdevps.dto.post.response.PostDTO;
import lotte.newdevps.exception.post.PostNotFoundException;
import lotte.newdevps.exception.user.UserNotFoundException;
import lotte.newdevps.ui.auth.UserSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public PostDTO save(UserSession session,PostSaveDTO postDto){
        User user = userRepository.findById(session.getId())
                .orElseThrow(UserNotFoundException::new);

//        MultipartFile imageFile = postDto.getImageFile();
//        String originalFilename = imageFile.getOriginalFilename();
//        String name = imageFile.getName();

        Post post = postRepository.save(PostSaveDTO.toEntity(user,postDto));
        return PostDTO.toDto(post);
    }

    public List<PostDTO> findByPostsAll(){
        return PostDTO.toDtoList(postRepository.findAll());
    }

    public PostDTO findByPostOne(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        return PostDTO.toDto(post);
    }

    public PostDTO updatePostOne(Long postId, PostUpdateDTO postDto){
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        post.updatePost(postDto);
        return PostDTO.toDto(post);
    }

    public void deletePostOne(Long postId){
        postRepository.deleteById(postId);
    }
}
