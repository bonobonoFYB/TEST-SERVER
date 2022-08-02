package com.example.API_test.repository;

import com.example.API_test.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface memberRepository extends JpaRepository<Member,Long> {
}
