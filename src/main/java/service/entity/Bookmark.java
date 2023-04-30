package service.entity;

import global.constants.BookmarkConstants;
import lombok.Getter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Getter
@ToString
public class Bookmark {

    private Long id;
    private String name;
    private Integer order;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    ////////////////////////////////////////////////////////////////////
    // Entity Factory
    public static Bookmark of(String name, int order) {
        Bookmark bookmark = new Bookmark();
        bookmark.name = name;
        bookmark.order = order;
        bookmark.createdAt = LocalDateTime.now();
        return bookmark;
    }

    public static Bookmark wifiBookmarkOf(ResultSet rs) throws SQLException {
        Bookmark bookmark = new Bookmark();
        bookmark.id = rs.getLong("b_id");
        bookmark.name = rs.getString("b_name");
        bookmark.order = rs.getInt("b_order_num");
        return bookmark;
    }

    public static Bookmark of(ResultSet rs) throws SQLException {
        Bookmark bookmark = new Bookmark();
        bookmark.id = rs.getLong(BookmarkConstants.FIELD_ID);
        bookmark.name = rs.getString(BookmarkConstants.FIELD_NAME);
        bookmark.order = rs.getInt(BookmarkConstants.FIELD_ORDER);
        bookmark.createdAt = LocalDateTime.parse(rs.getString(BookmarkConstants.FIELD_CREATED_AT));
        String tmpUpAt= rs.getString(BookmarkConstants.FIELD_UPDATED_AT);
        bookmark.updatedAt = tmpUpAt != null && !tmpUpAt.isEmpty() ? LocalDateTime.parse(tmpUpAt) : null;
        return bookmark;
    }

}
