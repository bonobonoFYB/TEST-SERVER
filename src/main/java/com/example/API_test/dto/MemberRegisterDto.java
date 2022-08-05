package com.example.API_test.dto;


import com.example.API_test.entity.Member;
import com.sun.istack.NotNull;
import lombok.*;


public class MemberRegisterDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private String name;
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
    public static class Response {
        private String name;
        private String email;

        public static Response entityResponse(@NotNull Member member) {
            return Response.builder()
                    .name(member.getName())
                    .email(member.getEmail())
                    .build();
        }
    }
}
