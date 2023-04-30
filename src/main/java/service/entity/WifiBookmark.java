package service.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WifiBookmark {

    private String id;
    private String wifiId;
    private String bookmarkId;
    private LocalDateTime createdAt;

}
