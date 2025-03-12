package com.example.jwt_0310.domain.member.entity;

import com.example.jwt_0310.global.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Setter @Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Member extends BaseEntity {
  private String mid, mname;

  @JsonIgnore // json 에서 밑에 내용 제외 (외부로 가는 것이 보안상 좋지 않기 때문에 정의)
  private String mpwd;
  private String accessToken;

  @Column(name = "refresh_token", length = 1024)
  private String refreshToken;
}
