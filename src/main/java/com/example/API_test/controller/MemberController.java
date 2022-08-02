package com.example.API_test.controller;

import com.example.API_test.dto.MemberRegisterDto;
import com.example.API_test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
