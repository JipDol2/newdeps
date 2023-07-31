package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.PostService;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.post.request.PostSaveDTO;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.dto.post.response.PostDTO;
import lotte.newdevps.ui.auth.Authentication;
import lotte.newdevps.ui.auth.UserSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    /**
     * 게시글 저장(P001)
     */
    @PostMapping
    public CommonResponseEntity savePost(@Authentication UserSession session, @RequestBody PostSaveDTO postDto){
        postService.save(session,postDto);
        return CommonResponseEntity.toResponseEntity(ResponseType.P001,null,0);
    }

    /**
     * 게시글 전체 목록 조회(P002)
     */
    @GetMapping("/all")
    public CommonListResponseEntity<PostDTO> findByPostsAll(@RequestParam Long id){
        List<PostDTO> allPosts = postService.findByPostsAll(id);
        return CommonListResponseEntity.toListResponseEntity(ResponseType.P002,allPosts,allPosts.size());
    }

    /**
     * 게시글 단건 조회(P003)
     */
    @GetMapping("/{postId}")
    public CommonResponseEntity<PostDTO> findByPostOne(@PathVariable("postId") String postId){
        return CommonResponseEntity.toResponseEntity(ResponseType.P003,postService.findByPostOne(postId),1);
    }
}
