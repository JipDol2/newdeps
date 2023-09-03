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
import lotte.newdevps.dto.bookmark.response.BookmarkListDTO;
import lotte.newdevps.dto.place.response.PlaceDTO;
import lotte.newdevps.dto.post.response.PostDTO;
import lotte.newdevps.exception.place.PlaceNotFoundException;
import lotte.newdevps.exception.post.PostNotFoundException;
import lotte.newdevps.exception.user.UserNotFoundException;
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

        User user = userRepository.findById(session.getId())
                .orElseThrow(()-> new UserNotFoundException());

        Place place = null;
        Post post = null;
        if (bookmarkDto.getBookmarkType().equals(BookmarkType.RECOMMEND_PLACE)) {
            place = placeRepository.findById(id)
                    .orElseThrow(() -> new PlaceNotFoundException());
        }
        if (bookmarkDto.getBookmarkType().equals(BookmarkType.POST)) {
            post = postRepository.findById(id)
                    .orElseThrow(() -> new PostNotFoundException());
        }

        Bookmark bookmark = Bookmark.builder()
                .bookmarkType(bookmarkDto.getBookmarkType())
                .user(user)
                .place(place)
                .post(post)
                .build();
        bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(LoginSession session,Long id,BookmarkSaveDTO bookmarkSaveDTO){
        User user = userRepository.findById(session.getId())
                .orElseThrow(() -> new UserNotFoundException());

        Place place=null;
        Post post=null;
        if(bookmarkSaveDTO.getBookmarkType().equals(BookmarkType.RECOMMEND_PLACE)){
            place = placeRepository.findById(id)
                    .orElseThrow(()->new PlaceNotFoundException());
        }
        if(bookmarkSaveDTO.getBookmarkType().equals(BookmarkType.POST)){
            post = postRepository.findById(id)
                    .orElseThrow(()->new PostNotFoundException());
        }
        bookmarkRepository.deleteBookmarkByUserAndPlaceOrPost(user,place,post);
    }

    public List<PostDTO> findBookmarkPostList(LoginSession session) {
        List<Bookmark> bookmarks = bookmarkRepository.findAllByBookmarkPost(session.getId());
        List<PostDTO> posts = bookmarks.stream()
                .map(bookmark -> PostDTO.toDto(bookmark.getPost()))
                .collect(Collectors.toList());
        return posts;
    }

    public List<PlaceDTO> findBookmarkPlaceList(LoginSession session) {
        List<Bookmark> bookmarks = bookmarkRepository.findAllByBookmarkPlace(session.getId());
        List<PlaceDTO> places = bookmarks.stream()
                .map(bookmark -> PlaceDTO.toPlaceDto(bookmark.getPlace()))
                .collect(Collectors.toList());
        return places;
    }

    public List<BookmarkListDTO> findByBookmarkList(LoginSession session) {
        List<BookmarkListDTO> bookmarks = bookmarkRepository.findByBookmarkList(session.getId());
        return bookmarks;
    }
}
