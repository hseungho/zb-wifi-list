package service.entity;

import global.constants.WifiBookmarkConstants;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Getter
@ToString
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

    public static WifiBookmark of(ResultSet rs) throws SQLException {
        WifiBookmark wifiBookmark = new WifiBookmark();
        wifiBookmark.id = rs.getLong(WifiBookmarkConstants.FIELD_ID);
        wifiBookmark.wifiId = rs.getString(WifiBookmarkConstants.FIELD_WIFI_ID);
        wifiBookmark.bookmarkId = rs.getLong(WifiBookmarkConstants.FIELD_BOOKMARK_ID);
        wifiBookmark.createdAt = LocalDateTime.parse(rs.getString(WifiBookmarkConstants.FIELD_CREATED_AT));
        return wifiBookmark;
    }

}
