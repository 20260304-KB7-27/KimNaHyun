package org.scoula.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.service.BoardService;
import org.scoula.config.RootConfig;
import org.scoula.config.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        RootConfig.class,
        ServletConfig.class
})
@WebAppConfiguration // 웹 환경 테스트임을 선언, MockMvc 활성화
@Slf4j
class BoardControllerTest {
    @Autowired
    BoardService service;

    @Autowired
    WebApplicationContext ctx;   // 웹 어플리케이션 컨텍스트 -> mockMVC 만들때 기반으로써 사용

    // 실제 서버를 사용하지 않고 컨트롤러를 호출 / 검증할 수 있게 해주는 가짜 MVC 객체
    private MockMvc mockMVC;

    @BeforeEach // 각 테스트 실행 전에
    public void setup(){
        this.mockMVC = MockMvcBuilders.webAppContextSetup(ctx).build(); // MockMvc 객체 생성
    }

    @Test
    public void list() throws Exception{
        // mockMvc.perform : 테스트코드에서 브라우저 대신 컨트롤러 HTTP 요청을 보내는 메서드
        log.info(mockMVC.perform(MockMvcRequestBuilders.get("/board/list")) // ResultActions 리턴
                .andReturn() // 요청 처리 결과 (MvcResult)
                .getModelAndView() // 컨트롤러가 반환한 뷰 + 모델 -> ModelAndView
                .getModelMap() // 뷰에 전달되는 모델 데이터 -> Model(ModelMap)
                .toString()
        );
    }

    // [POST] /board/create -> 핸들러 테스트
    @Test
    public void create() throws Exception{
        // View 이름 받아오기
        String resultPage = mockMVC.perform(
                MockMvcRequestBuilders.post("/board/create")
                        .param("title", "테스트 제목")
                        .param("writer", "테스트 작성자")
                        .param("content", "테스트 내용"))
                .andReturn()
                .getModelAndView()
                .getViewName();

            log.info(resultPage);
    }

}