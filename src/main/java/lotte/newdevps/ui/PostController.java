package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.PostService;
import lotte.newdevps.dto.post.request.PostSaveDTO;
import lotte.newdevps.dto.post.ListPostResponse;
import lotte.newdevps.dto.post.response.PostDTO;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void savePost(@RequestBody PostSaveDTO postDto){
        postService.save(postDto);
    }

    @GetMapping("/all")
    public ListPostResponse<PostDTO> findByPostsAll(@RequestParam Long id){
        return postService.findByPostsAll(id);
    }
}
