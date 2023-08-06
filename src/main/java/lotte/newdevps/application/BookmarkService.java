package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.bookmark.Bookmark;
import lotte.newdevps.domain.bookmark.BookmarkRepository;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.place.PlaceRepository;
import lotte.newdevps.domain.placeBookmark.PlaceBookmark;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.bookmark.BookmarkSaveDTO;
import lotte.newdevps.exception.place.PlaceNotFoundException;
import lotte.newdevps.ui.auth.UserSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkService {

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final BookmarkRepository bookmarkRepository;

    public void saveBookmark(UserSession session, Long placeId,BookmarkSaveDTO bookmarkDto) {

        User user = userRepository.findById(session.getId()).get();

        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFoundException());

        Bookmark bookmark = Bookmark.builder()
                .bookmarkType(bookmarkDto.getBookmarkType())
                .user(user)
                .build();

        PlaceBookmark placeBookmark = PlaceBookmark.builder()
                .bookmark(bookmark)
                .place(place)
                .build();
        placeBookmark.setBookmark(bookmark);

        bookmarkRepository.save(bookmark);
    }
}
