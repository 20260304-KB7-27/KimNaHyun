package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
 * @Controller
 * @ResponseBody -> body영역에 값을 직접 작성하겠다.
 * */
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor // -> final / not null로 된 필드를 포함하는 생성자를 만들어줌
public class BoardController {

    private final BoardService service;


    // 전체조회
    @GetMapping("/list")
    public List<BoardDTO> getList() {

        return service.getList();
    }


    // ResponseEntity : Header, Body에 원하는 내용을 작성하고 싶을때
    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList2() {

        return ResponseEntity.ok(service.getList());
    }

    // 단건조회 api/board/{no}
    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> getById(@PathVariable Long no) {

        return ResponseEntity.ok(service.get(no));
    }


    // [POST] /api/board  -> 생성
    @PostMapping("")
    public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO boardDTO) {

        // 201 -> 새로운데이터가 입력되었다
//        return ResponseEntity.ok(service.create(boardDTO));
//        return ResponseEntity
//                .created(URI.create("/board/" + boardDTO.getNo()))
//                .body(service.create(boardDTO));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(boardDTO));
    }


    // 수정요청 [PUT] /api/board/{no}
    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(
            @PathVariable Long no, @RequestBody BoardDTO boardDTO) {

        boardDTO.setNo(no);

        return ResponseEntity.ok(service.update(boardDTO));
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(@PathVariable Long no) {

        return ResponseEntity.ok(service.delete(no));
    }

}
