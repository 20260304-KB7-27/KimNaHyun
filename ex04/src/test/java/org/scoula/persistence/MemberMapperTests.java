package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.member.domain.MemberVO;
import org.scoula.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class}) // 설정 클래스 지정
@Log4j2
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper; // MyBatis가 자동 구현한 빈 주입

    @Test
    @DisplayName("MemberMapper 주입 테스트")
    public void testMapperExtension() {
        log.info("인터페이스 주입 여부 확인");
        assertNotNull(memberMapper, "MemberMapper가 정상적으로 주입되지 않았습니다.");
        log.info("주입된 대리 객체 클래스명: " + memberMapper.getClass().getName());
    }

    @Test
    @DisplayName("회원 등록(Create) 및 전체 목록 조회(Read) 통합 테스트")
    public void testInsertAndGetList() {
        // 1. 등록할 임시 데이터 세팅
        MemberVO newMember = MemberVO.builder()
                .username("hong123")
                .password("p1234")
                .email("hong@scoula.org")
                .birthYear(2000)
                .build();

        log.info("회원 등록 시도...");
        memberMapper.insert(newMember);
        log.info("회원 등록 완료!");

        // 2. 전체 목록 조회하여 방금 넣은 데이터가 잘 들어갔는지 확인
        log.info("전체 회원 목록 조회 시도...");
        List<MemberVO> list = memberMapper.getList();

        assertNotNull(list);
        assertTrue(list.size() > 0, "조회된 회원 목록이 비어있습니다.");

        // 전체 리스트 출력해보며 확인
        list.forEach(member -> log.info("조회된 회원 정보: " + member));
    }
}