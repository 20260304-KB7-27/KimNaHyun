package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    void create(){
        BoardVO board = BoardVO.builder()
                .title("새로운 개시글 제목")
                .content("새로운 게시글 내용")
                .writer("testUser")
                .build();

        mapper.create(board);
        log.info(board);
    }

    @Test
    void update() {
        BoardVO board = BoardVO.builder()
                .title("변경된 개시글 제목")
                .content("변경된 게시글 내용")
                .writer("testUser")
                .build();

        mapper.update(board);
        log.info(board);
    }
}