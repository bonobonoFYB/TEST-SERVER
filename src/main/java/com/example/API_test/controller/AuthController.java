package com.example.API_test.controller;

import com.example.API_test.dto.MemberLoginDto;
import com.example.API_test.jwt.JwtFilter;
import com.example.API_test.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<Map> authorize(@Valid @RequestBody MemberLoginDto.Request request) {

        // 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = // UsernamePasswordAuthenticationToken 토큰 생성
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPw()); // 이메일과 패스워드를 파라미터로 받고

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // authenticate 가 실행될때 CustomUserDatilsService의 loadUserByUsername 메소드가 실행
        // 실행 후 결과 값을 가지고 authentication 객체를 생성
        SecurityContextHolder.getContext().setAuthentication(authentication); // 생성된 객체를 SecurityContext에 저장

        String jwt = tokenProvider.createToken(authentication); // 저장된 인증정보를 기준으로 JWT 토큰 생성

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        // JWT 토큰을 Response Header에 삽입
        Map<String, Boolean> map = new HashMap<>();
        map.put("result", true);
        // Map 형식으로 결과값이 true 임을 넘긴다.

//        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
        return new ResponseEntity<>(map, httpHeaders, HttpStatus.OK);
    }
}
