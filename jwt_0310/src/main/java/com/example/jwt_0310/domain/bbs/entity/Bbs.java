package com.example.jwt_0310.domain.bbs.entity;

import com.example.jwt_0310.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@SuperBuilder
@AllArgsConstructor

public class Bbs extends BaseEntity {
}
