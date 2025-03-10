package com.example.jwt_0310.global.jpa;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ToString(callSuper = true)
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동생성
  private Long b_idx;

  @CreatedDate
  private LocalDateTime write_date;
}
