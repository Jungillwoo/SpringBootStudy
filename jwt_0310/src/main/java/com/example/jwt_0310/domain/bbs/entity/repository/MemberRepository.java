package com.example.jwt_0310.domain.bbs.entity.repository;

import com.example.jwt_0310.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByMid(String mid);
  Optional<Member> findByRefreshToken(String refreshToken);
}
