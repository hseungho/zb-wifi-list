package service.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WifiBookmark {

    private Long id;
    private String wifiId;
    private Long bookmarkId;
    private LocalDateTime createdAt;

    public void associate(Wifi wifi) {
        this.wifiId = wifi.getId();
    }

    public void associate(Bookmark bookmark) {
        this.bookmarkId = bookmark.getId();
    }

    ////////////////////////////////////////////////////////////////////
    // Entity Factory
    public static WifiBookmark of() {
        WifiBookmark wifiBookmark = new WifiBookmark();
        wifiBookmark.createdAt = LocalDateTime.now();
        return wifiBookmark;
    }

}
