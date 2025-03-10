package com.example.jwt_0310.domain.bbs.entity;

import com.example.jwt_0310.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true) // 부모가 가지는 함수사용(필드 포함)
public class Bbs extends BaseEntity {

  private Long hit;
  private Long state;
  private String title, content, writer;
}
