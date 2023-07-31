package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.post.PostRepository;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.post.request.PostSaveDTO;
import lotte.newdevps.dto.post.response.PostDTO;
import lotte.newdevps.exception.post.PostNotFoundException;
import lotte.newdevps.exception.user.UserNotFoundException;
import lotte.newdevps.ui.auth.UserSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void save(UserSession session,PostSaveDTO postDto){
        User user = userRepository.findById(session.getId())
                .orElseThrow(UserNotFoundException::new);

        Post post = PostSaveDTO.toEntity(user,postDto);
        postRepository.save(post);
    }

    public List<PostDTO> findByPostsAll(Long userId){
        return PostDTO.toDtoList(postRepository.findByAll(userId));
    }

    public PostDTO findByPostOne(String postId){
        Post post = postRepository.findById(Long.parseLong(postId))
                .orElseThrow(PostNotFoundException::new);
        return PostDTO.toDto(post);
    }
}
