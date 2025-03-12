package com.example.jwt_0310.domain.member.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemVO {

  private String mid;
  private String mpwd;
  private String mname;
}
//내용을 보면 Member.java 로 사용해도 될 것 같지만 절대 그렇게 할 수가 없다. 이유는?
// 앞서 Member.java 파일에서 비밀번호는 보안상 JSON 처리를 하지 못하도록
// @JsonIgnore
// 처리를 했기 때문에 Front 단에서 JSON 으로 전달하는 파라미터들 중 mpwd 라는 값만 저장하지 못 하여 로그인 작업을 하지 못한다.