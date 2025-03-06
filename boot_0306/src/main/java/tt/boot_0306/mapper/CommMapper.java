package tt.boot_0306.mapper;

import org.apache.ibatis.annotations.Mapper;
import tt.boot_0306.vo.CommVO;

import java.util.List;

@Mapper
public interface CommMapper {

  List<CommVO> commList(String b_idx);
}
