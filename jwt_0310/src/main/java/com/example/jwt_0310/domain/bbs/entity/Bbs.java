package com.example.jwt_0310.domain.bbs.entity;

import com.example.jwt_0310.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true) // 부모가 가지는 함수사용(필드 포함)
public class Bbs extends BaseEntity {

  @Column(columnDefinition = "bigint default 0")
  private Long hit;

  @Column(columnDefinition = "bigint default 0")
  private Long status;

  @Column
  private String title, content, writer;

  @PrePersist // save 함수로 데이터가 저장되기 전에 수행
  public void initStatus() {
    if (status == null) {
      status = 0L;
      hit = 0L;
    }
  }
}
