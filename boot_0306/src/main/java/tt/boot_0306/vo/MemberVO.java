package tt.boot_0306.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {

  private String m_idx, m_id, m_pw, m_name, m_email, m_phone, reg_date;
}
