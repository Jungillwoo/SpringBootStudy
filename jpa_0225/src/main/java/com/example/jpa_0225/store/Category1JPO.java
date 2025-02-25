package com.example.jpa_0225.store;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "category1_t")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Category1JPO {

    @Id
    @GeneratedValue
    private long idx;
    private String cName; // c_name 으로 db에 저장 (카멜기법 -> _ 언더바로)

    @Column(name = "`desc`") // desc는 MySQL의 예약어이므로 컬럼명으로 사용하면 안 된다.
                             // MySQL에서 desc는 DESCRIBE의 줄임말로, 테이블 구조를 조회할 때 사용된다.
    private String desc;
    private int status;

    @OneToMany // OneToMany는 현재 객체가 1이고 연결테이블에서는 여러 개가 있다는 뜻이므로
               // 현재 테이블에서 1개만 존재하도록 하는 것을 구별하는 것은 기본키다. idx가 기본키이고, 
               // 연결되는 테이블에서 여러 개가 참조되는 컬럼이 category1이 되어야 한다. 
    @JoinColumn(name = "category1")
    private List<ProductJPO> list;
}
/*
    Spring Data JPA 는 메서드 이름을 분석해서 JPQL 쿼리를 실행한다.
    메서드 명을 이용한 쿼리 실행은 조회(select)에 해당되며 
    @Query 어노테이션을 이용하면 쿼리를 직접 작성할 수 있다.

    AND 조건 : findByEnameAndJob() : WHERE ename = ? AND job = ?
    OR 조건 : findByEnameOrJob() : WHERE ename = ? OR job = ?
    Like 조건 : findByEnameLike() : WHERE ename LIKE ?
    Between 조건 : findBySalBetween() : WHERE sal BETWEEN ? AND ?
 */
