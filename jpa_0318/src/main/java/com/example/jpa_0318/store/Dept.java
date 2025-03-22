package com.example.jpa_0318.store;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "dept")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
// @JsonIgnoreProperties는 JSON 직렬화/역직렬화 과정에서 특정 필드를 무시하도록 지정하는 Jackson 어노테이션이다.
// 목적: Hibernate에서 프록시 객체를 사용하는 경우, JSON 직렬화 시 hibernateLazyInitializer와 handler 필드가
// 자동으로 생성되는데, 이를 무시하여 직렬화 오류를 방지한다.
// 적용 대상: Dept 엔터티가 JSON으로 변환될 때 Hibernate가 자동으로 추가하는 프록시 필드들을 제외한다.
public class Dept {

  @Id
  @Column(name = "deptno")
  private Long deptno;
  private String dname;
  private int loc_code;

  @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  // @OneToMany: Dept 엔터티는 여러 개의 Emp 엔터티를 가질 수 있는 1:N 관계를 의미한다.
  // mappedBy = "dept": Emp 엔터티의 dept 필드가 관계의 주인이므로, Dept 테이블에는 외래 키(deptno)가 생성되지 않고, Emp 테이블에서 deptno가 외래 키로 관리된다.
  // cascade = CascadeType.ALL: Dept 엔터티에 대한 변경(삽입, 수정, 삭제)이 발생하면 관련된 모든 Emp 엔터티에도 동일한 작업이 적용된다.
  // fetch = FetchType.LAZY: 기본적으로 elist(Emp 리스트)를 지연 로딩하여 필요할 때만 가져온다.
  @JsonIgnoreProperties({"dept"})
  // 목적: Dept 엔터티가 OneToMany 관계로 Emp 엔터티를 포함하고 있다.
  // 만약 Emp 엔터티에도 @ManyToOne으로 Dept를 매핑하면, 서로 참조하는 관계(순환 참조, Infinite Recursion) 가 발생할 수 있다.
  // 적용 대상: elist 필드가 JSON 변환될 때 dept 필드를 제외하여 순환 참조를 방지한다.
  private List<Emp> elist;
}
