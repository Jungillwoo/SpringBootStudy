package com.example.jwt_0310.domain.bbs.entity.repository;

import com.example.jwt_0310.domain.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByMid(String mid);
  Optional<Member> findByRefreshToken(String refreshToken);

  // refreshToken 을 Update 하는 기능
  @Modifying
  @Transactional
  @Query("UPDATE Member m SET m.refreshToken = :refreshToken WHERE m.mid = :mid")
  void updateRefreshToken(@Param("mid") String mid, @Param("refreshToken") String refreshToken);
}
