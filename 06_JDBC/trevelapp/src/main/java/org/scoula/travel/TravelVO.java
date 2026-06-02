package org.scoula.travel;/*
 * VO (Value Object) -> 데이터베이스에 종속되는
 * - 데이터 자체를 담는 객체
 * - 주로 DB 테이블의 한 행을 객체로 표현
 *
 * DTO (Data Transfer Object)
 * - 계층간 데이터 전달만을 위해 사용하는 객체
 * - 비즈니스로직에 전달에 필요한 필드만 담음
 *
 * */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.travel.domain.TravelImageVO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class TravelVO {
    private Long no;
    private String distrct;`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````/]]=\\````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````
    private String phone;


    List<TravelImageVO> images;
}