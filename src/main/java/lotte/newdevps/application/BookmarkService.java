package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.bookmark.Bookmark;
import lotte.newdevps.domain.bookmark.BookmarkRepository;
import lotte.newdevps.domain.bookmark.BookmarkType;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.place.PlaceRepository;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.post.PostRepository;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.bookmark.request.BookmarkSaveDTO;
import lotte.newdevps.dto.post.response.PostDTO;
import lotte.newdevps.exception.place.PlaceNotFoundException;
import lotte.newdevps.exception.post.PostNotFoundException;
import lotte.newdevps.ui.auth.LoginSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkService {

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PostRepository postRepository;
    private final BookmarkRepository bookmarkRepository;

    public void saveBookmark(LoginSession session, Long id, BookmarkSaveDTO bookmarkDto) {

        User user = userRepository.findById(session.getId()).get();

        Place place = null;
        Post post = null;
        if (bookmarkDto.getBookmarkType().equals(BookmarkType.RECOMMEND_PLACE)) {
            place = placeRepository.findById(id)
                    .orElseThrow(() -> new PlaceNotFoundException());
        }else{
            post = postRepository.findById(id)
                    .orElseThrow(()-> new PostNotFoundException());
        }

        Bookmark bookmark = Bookmark.builder()
                .bookmarkType(bookmarkDto.getBookmarkType())
                .user(user)
                .place(place)
                .post(post)
                .build();

        bookmarkRepository.save(bookmark);
    }

    public List<?> findBookmarkPlaceList() {
        return null;
    }

    public List<PostDTO> findBookmarkPostList(LoginSession session) {
        List<Bookmark> bookmarks = bookmarkRepository.findAllByPostBookmark(session.getId());
        List<PostDTO> posts = bookmarks.stream()
                .map(bookmark -> PostDTO.toDto(bookmark.getPost()))
                .collect(Collectors.toList());
        return posts;
    }
}
