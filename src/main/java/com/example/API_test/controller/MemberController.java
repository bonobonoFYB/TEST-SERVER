package com.example.API_test.controller;

import com.example.API_test.dto.MemberRegisterDto;
import com.example.API_test.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MemberController {

    @PostMapping("/register")
    public MemberRegisterDto.Response registerMember(
            @Valid @RequestBody final MemberRegisterDto.Request request
    ) {
        return MemberService.registerMember(request);
    }
}
