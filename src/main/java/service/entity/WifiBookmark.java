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
    private Wifi wifi;
    private Bookmark bookmark;
    private LocalDateTime createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void associate(Wifi wifi) {
        this.wifi = wifi;
    }

    public void associate(Bookmark bookmark) {
        this.bookmark = bookmark;
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
        wifiBookmark.createdAt = LocalDateTime.parse(rs.getString(WifiBookmarkConstants.FIELD_CREATED_AT));
        return wifiBookmark;
    }

}
