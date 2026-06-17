package org.scoula.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private Long no;
    private String username;
    private String password;
    private String email;
    private Integer birthYear;
    private Date regDate;
    private Date updatedDate;
}