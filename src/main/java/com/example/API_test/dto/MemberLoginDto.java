package com.example.API_test.dto;

import com.example.API_test.entity.Member;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

public class MemberLoginDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        @NotNull
        private String email;
        @NotNull
        private String pw;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String pw;
        private String email;

        public static MemberRegisterDto.Response entityResponse(@NotNull Member member){
            return MemberRegisterDto.Response.builder()
                    .name(member.getName())
                    .email(member.getEmail())
                    .build();
        }
    }
}
