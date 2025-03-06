package tt.boot_0306.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tt.boot_0306.mapper.CommMapper;
import tt.boot_0306.vo.CommVO;

import java.util.List;

@Service
public class CommService {

  @Autowired
  private CommMapper commMapper;

  public List<CommVO> commVOList(String b_idx) {
    return commMapper.commList(b_idx);
  }
}
