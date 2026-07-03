package org.scoula.member.controller;


import lombok.RequiredArgsConstructor;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.service.MemberService;
import org.scoula.utils.UploadFiles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    final MemberService service;

    // 회원 중복검사
    @GetMapping("/checkusername/{username}")
    public ResponseEntity<Boolean> checkUserName(@PathVariable String username){
        return ResponseEntity.ok().body(service.checkDuplicate(username));
    }

    // 회원가입
    @PostMapping("")
    public ResponseEntity<MemberDTO> join(MemberJoinDTO member){
        return ResponseEntity.ok(service.join(member));
    }

    // "C:/upload/avatar/username.png"
    // 아바타 파일 저장
    @GetMapping("/{username}/avatar")
    public void getAvatar(@PathVariable String username, HttpServletResponse response){

        String avatarPath = "c:/upload/avatar/" + username + ".png";
        File file = new File(avatarPath);

        // 해당 경로에 파일(이미지)가 없으면 기본 이미지로 변경
        if(!file.exists()){
            file = new File("C:/upload/avatar/default.png");
        }

        UploadFiles.downloadImage(response, file);
    }




}

