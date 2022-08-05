package com.example.API_test.controller;

import com.example.API_test.dto.DefaultResultDto;
import com.example.API_test.dto.MemberLoginDto;
import com.example.API_test.dto.MemberRegisterDto;
import com.example.API_test.model.TrueLogin;
import com.example.API_test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/register")
    public MemberRegisterDto.Response registerMember(
            @Valid @RequestBody final MemberRegisterDto.Request request
    ) {
        return memberService.registerMember(request);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<DefaultResultDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberRegisterDto.Response> getUserInfo(
            @Valid @PathVariable String email
    ) {
        return ResponseEntity.ok(memberService.getUserWithAuthorities(email));
    }
}
