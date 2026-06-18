package org.scoula.board.domain;

import lombok.*;

import java.util.Date;

// Mybatis 진영에서의 VO -> DB의 한 행에 매핑되는 객체
@Data // 총 집합 : GETTER, SETTER, ToString, EqualsAndHashCode, RequireArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {

    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}
