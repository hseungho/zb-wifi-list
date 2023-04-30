package service.entity;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class History {

    private Long id;
    private Double lat;
    private Double lnt;
    private LocalDateTime createdAt;

}
