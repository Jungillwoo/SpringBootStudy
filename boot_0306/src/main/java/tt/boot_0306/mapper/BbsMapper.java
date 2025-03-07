package tt.boot_0306.mapper;

import org.apache.ibatis.annotations.Mapper;
import tt.boot_0306.vo.BbsVO;

import java.util.List;
import java.util.Map;

@Mapper
public interface BbsMapper {
  // 연결된 mapper문서에 있는 id와 동일한 이름으로 함수 정의
  List<BbsVO> list(Map<String, Object> map);
  int totalCount(Map<String, Object> map);
  BbsVO get_bbs(String b_idx);
  int add(BbsVO bbs);
}
