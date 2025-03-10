package com.example.jwt_0310.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ResultData<T> {

  private int totalCount;
  private String message;
  private T data;

  public static <T> ResultData<T> of(int totalCount, String msg, T data) {
    return new ResultData<>(totalCount, msg, data);
  }

  // 오버로딩 (인자 수가 다르면 메서드명 같아도 된다.)
  public static <T> ResultData<T> of(int totalCount, String msg) {
    return of(totalCount, msg, null);
  }
}
