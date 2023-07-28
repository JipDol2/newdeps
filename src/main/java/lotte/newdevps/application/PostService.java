package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.post.PostRepository;
import lotte.newdevps.dto.post.ListPostResponse;
import lotte.newdevps.dto.post.request.PostSaveDTO;
import lotte.newdevps.dto.post.response.PostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public void save(PostSaveDTO postDto){
        Post post = PostSaveDTO.toEntity(postDto);
        postRepository.save(post);
    }

    public ListPostResponse findByPostsAll(Long userId){
        List<Post> findByPosts = postRepository.findByAll(userId);
        return new ListPostResponse(PostDTO.toDtoList(findByPosts),findByPosts.size());
    }
}
