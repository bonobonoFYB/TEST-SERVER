package com.example.API_test.service;

import com.example.API_test.dto.MemberLoginDto;
import com.example.API_test.dto.MemberRegisterDto;
import com.example.API_test.entity.Member;
import com.example.API_test.repository.memberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final memberRepository memberRepository;

    @Transactional
    public MemberRegisterDto.Response registerMember(MemberRegisterDto.Request request) {
        return MemberRegisterDto.Response.entityResponse(
                memberRepository.save(
                        Member.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .pw(request.getPw())
                                .build()
                )
        );
    }

    public Member loginMember(MemberLoginDto.Request request) {
        return memberRepository.findByEmailAndPw(
                request.getEmail(), request.getPw()
        ).orElseThrow(
                () -> new RuntimeException("로그인 실패")
        );
    }
}
