package org.scoula.controller;


import lombok.extern.log4j.Log4j2;
import org.scoula.dto.LoginRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
public class ExampleController2 {
    // String -> ViewSolver 완성
    // void 반환 타입일 경우 요청 URL 기준으로 뷰 이름을 추론
    // @RequestMapping(value = "", method = RequestMethod.GET)

    @GetMapping("/index")
    public void get(){
        log.info("======> Void 반환 타입 url로 추론됨..");
    }

    /*
    * ModelAttribute
    * -> 요청 파라미터를 자바 객체에 바인딩하는 역할
    * - 객체의 Setter를 통해 값이 주입됨
    * - 생략 가능
    * */
    @GetMapping("/example01")
    public String get(/*@ModelAttribute*/ LoginRequestDTO dto, Model model){
        log.info("======> Void 반환 타입 url로 추론됨..");

        log.info("example01 ======> {}", dto.getId());
        log.info("example01 ======> {}", dto.getPassword());

        model.addAttribute("dto", dto);

        return "page/dtoMappingResult";
        // http://localhost:8080/example01?id=1111&password=2222
    }

    /*
    * RequestParam
    * - 요청 파라미터를 개별 변수에 바인딩 할 때 사용
    * - 리스트 / 배열로도 받을 수 있음
    * */

    @GetMapping("/example02")
    public String get2(
        @RequestParam(name="id", defaultValue = "defaultID") String id,
        @RequestParam(name="password", required = false) String password,
        Model model) {

        log.info("example02 ======> {}", id);
        log.info("example02 ======> {}", password);

        model.addAttribute("loginRequestDTO", new LoginRequestDTO(id, password));

        return "page/dtoMappingResult";
        // http://localhost:8080/example02?id=1111&password=2222
    }


    /*
    * @PathVariable
    * - URL 경로에 포함된 값을 추출해서 변수에 바인딩
    * */

    @GetMapping("/example04/{id}")
    public String get(@PathVariable int id, Model model){
        log.info("======> Void 반환 타입 url로 추론됨..");

        log.info("example01 ======> {}", id);

        model.addAttribute("message", id);

        return "page/mappingResult";
        // http://localhost:8080/example04/567
    }

}
