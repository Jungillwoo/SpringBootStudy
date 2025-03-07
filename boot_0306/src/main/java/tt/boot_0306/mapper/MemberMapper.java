package tt.boot_0306.mapper;

import org.apache.ibatis.annotations.Mapper;
import tt.boot_0306.vo.MemberVO;

@Mapper
public interface MemberMapper {
    int reg(MemberVO vo);
    MemberVO login(String m_id);
}
