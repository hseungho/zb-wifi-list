package service.entity;

import global.constants.HistoryConstants;
import lombok.*;
import service.controller.dto.HistorySaveRequestDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class History {

    private Long id;
    private Double lat;
    private Double lnt;
    private LocalDateTime createdAt;


    ////////////////////////////////////////////////////////////////////
    // Entity Factory
    public static History of(HistorySaveRequestDto dto) {
        History history = new History();
        history.lat = dto.getLat();
        history.lnt = dto.getLnt();
        history.createdAt = LocalDateTime.now();
        return history;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static History of(ResultSet rs) throws SQLException {
        return History.of(
                rs.getLong(HistoryConstants.FIELD_ID),
                rs.getDouble(HistoryConstants.FIELD_LAT),
                rs.getDouble(HistoryConstants.FIELD_LNT),
                LocalDateTime.parse(rs.getString(HistoryConstants.FIELD_CREATED_AT))
        );
    }

    public static History of(Long id, Double lat, Double lnt, LocalDateTime createdAt) {
        return new History(id, lat, lnt, createdAt);
    }
}
