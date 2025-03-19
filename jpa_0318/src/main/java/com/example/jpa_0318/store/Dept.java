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
public class Dept {

  @Id
  @Column(name = "deptno")
  private Long deptno;
  private String dname;
  private int loc_code;

  @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnoreProperties({"dept"})
  private List<Emp> elist;
}
