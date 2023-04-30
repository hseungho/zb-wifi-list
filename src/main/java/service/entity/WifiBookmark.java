package service.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WifiBookmark {

    private Long id;
    private String wifiId;
    private Long bookmarkId;
    private LocalDateTime createdAt;

    public WifiBookmark() {
        this.createdAt = LocalDateTime.now();
    }

    public void associate(Wifi wifi) {
        this.wifiId = wifi.getId();
    }

    public void associate(Bookmark bookmark) {
        this.bookmarkId = bookmark.getId();
    }

}
