package service.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Bookmark {

    private String id;
    private String name;
    private Integer order;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
