package com.example.API_test.dto;

import com.example.API_test.entity.Member;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResultDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    public static DefaultResultDto Response(Member member) {

        if(member == null) return null;

        return DefaultResultDto.builder()
                .name(member.getName())
                .build();
    }
}
