package com.example.API_test.service;

import com.example.API_test.Exception.DuplicateMemberException;
import com.example.API_test.dto.MemberLoginDto;
import com.example.API_test.dto.MemberRegisterDto;
import com.example.API_test.entity.Authority;
import com.example.API_test.entity.Member;
import com.example.API_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberRegisterDto.Response registerMember(MemberRegisterDto.Request request) {
        if (memberRepository.findOneWithAuthoritiesByEmail(request.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return MemberRegisterDto.Response.entityResponse(
                memberRepository.save(
                        Member.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .authorities(Collections.singleton(authority))
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
