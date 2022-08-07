package com.example.API_test.service;

import com.example.API_test.Exception.DuplicateMemberException;
import com.example.API_test.Util.SecurityUtil;
import com.example.API_test.dto.DefaultResultDto;
import com.example.API_test.dto.MemberRegisterDto;
import com.example.API_test.entity.Authority;
import com.example.API_test.entity.Member;
import com.example.API_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public MemberRegisterDto.Response registerMember(MemberRegisterDto.Request request) {
        if (memberRepository.findOneWithAuthoritiesByEmail(request.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        // 권한 정보 생성
        return MemberRegisterDto.Response.entityResponse( // 유저 정보 생성
                memberRepository.save(
                        Member.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .authorities(Collections.singleton(authority))
                                .pw(passwordEncoder.encode(request.getPw()))
                                .build()
                )
        );
    }

    @Transactional(readOnly = true)
    public MemberRegisterDto.Response getUserWithAuthorities(String email) {
        return MemberRegisterDto.Response.entityResponse(memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    @Transactional(readOnly = true)
    public DefaultResultDto getMyUserWithAuthorities() {
        // getCurrentUsername 은 해당 프젝에서는 email 임 !
        String a = DefaultResultDto.Response(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null)).getName();
        System.out.println("===================");
        log.info(a);
        System.out.println("===================");

        return DefaultResultDto.Response(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null));
    }

}
