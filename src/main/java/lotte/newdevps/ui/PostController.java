package lotte.newdevps.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.PostService;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.post.request.PostSaveDTO;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.dto.post.request.PostUpdateDTO;
import lotte.newdevps.dto.post.response.PostDTO;
import lotte.newdevps.dto.post.response.PostDTOInterface;
import lotte.newdevps.ui.auth.Authentication;
import lotte.newdevps.ui.auth.LoginSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    /**
     * 게시글 저장(P001) - 추후 이미지도 받아야됨
     */
    @PostMapping
    public CommonResponseEntity<PostDTO> savePost(@Authentication LoginSession session, @ModelAttribute @Valid PostSaveDTO postDto){
        return CommonResponseEntity.toResponseEntity(ResponseType.P001,postService.save(session, postDto),0);
    }

    /**
     * 게시글 전체 목록 조회(P002)
     */
    @GetMapping("/all")
    public CommonListResponseEntity<PostDTOInterface> findByPostsAll(@Authentication LoginSession session){
        List<PostDTOInterface> allPosts = postService.findByPostsAll(session.getId());
        return CommonListResponseEntity.toListResponseEntity(ResponseType.P002,allPosts,allPosts.size());
    }

    /**
     * 게시글 단건 조회(P003)
     */
    @GetMapping("/{postId}")
    public CommonResponseEntity<PostDTO> findByPostOne(@PathVariable("postId") Long postId){
        return CommonResponseEntity.toResponseEntity(ResponseType.P003,postService.findByPostOne(postId),1);
    }

    /**
     * 게시글 업데이트 (P004)
     */
    @PutMapping("/{postId}")
    public CommonResponseEntity<PostDTO> updatePostOne(@PathVariable("postId") Long postId, @RequestBody PostUpdateDTO postDto){
        return CommonResponseEntity.toResponseEntity(ResponseType.P004,postService.updatePostOne(postId,postDto),1);
    }

    /**
     * 게시글 삭제(P005)
     */
    @DeleteMapping("/{postId}")
    public CommonResponseEntity<Void> deletePostOne(@PathVariable("postId") Long postId){
        postService.deletePostOne(postId);
        return CommonResponseEntity.toResponseEntity(ResponseType.P005,null,0);
    }
}
