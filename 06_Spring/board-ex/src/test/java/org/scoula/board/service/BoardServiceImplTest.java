package org.scoula.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("존재하는 게시글 조회 시 정상적으로 DTO를 반환한다.")
    void getSuccess() {
        // Given: DB에 확실히 존재하는 번호 (예: 1번)
        Long testNo = 1L;

        // When
        BoardDTO board = boardService.get(testNo);

        // Then
        assertNotNull(board);
        assertEquals(testNo, board.getNo());
        log.info("조회된 게시글 : {}", board);
    }

    @Test
    @DisplayName("존재하지 않는 게시글 조회 시 NoSuchElementException이 발생한다.")
    void getNotFoundException() {
        // Given: DB에 절대 없을 법한 가짜 번호 (예: -1번 또는 아주 큰 수)
        Long nonExistentNo = -999L;

        // When & Then
        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> {
            boardService.get(nonExistentNo);
        });

        log.info("기대했던 예외 메시지 : {}", e.getMessage());
    }

    @Test
    void insert(){
        BoardDTO board = BoardDTO.builder()
                .title("새로운 개시글 제목")
                .content("새로운 게시글 내용")
                .writer("testUser")
                .build();

        boardService.create(board);
    }
}