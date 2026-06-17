package org.scoula.member.mapper;

import org.scoula.member.domain.MemberVO;
import java.util.List;

public interface MemberMapper {

    // Create: 회원 등록
    public void insert(MemberVO member);

    // Read: 회원 전체 조회
    public List<MemberVO> getList();

    // Read: 특정 회원 조회 (PK인 no 사용)
    public MemberVO read(Long no);

    // Update: 회원 정보 수정
    public int update(MemberVO member);

    // Delete: 회원 탈퇴/삭제
    public int delete(Long no);
}