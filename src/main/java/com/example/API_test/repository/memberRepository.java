package com.example.API_test.repository;

import com.example.API_test.dto.MemberLoginDto;
import com.example.API_test.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface memberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByEmailAndPw(String email, String pw);
}
