package com.example.bbs_0220.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommVO {
    private String c_idx, writer, content, pwd, write_date, ip, b_idx;
    private int status;
}
