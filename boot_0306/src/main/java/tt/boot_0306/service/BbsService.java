package tt.boot_0306.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tt.boot_0306.mapper.BbsMapper;
import tt.boot_0306.vo.BbsVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BbsService {

  @Autowired
  private BbsMapper bbsMapper;

  public int getTotalCount(String bname, String searchType, String searchValue) {
    Map<String, Object> map = new HashMap<>();

    map.put("bname", bname);
    if (searchType != null && !"".equals(searchType)) {
      map.put("searchType", searchType);
    }
    if (searchValue != null && !"".equals(searchValue)) {
      map.put("searchValue", searchValue);
    }

    return bbsMapper.totalCount(map);
  }

  public BbsVO[] getList(String bname, String searchType, String searchValue, int begin, int end) {
    BbsVO[] ar = null;
    Map<String, Object> map = new HashMap<>();

    map.put("bname", bname);
    if (searchType != null && !"".equals(searchType)) {
      map.put("searchType", searchType);
    }
    if (searchValue != null && !"".equals(searchValue)) {
      map.put("searchValue", searchValue);
    }
    map.put("begin", begin);
    map.put("end", end);

    List<BbsVO> list = bbsMapper.list(map);
    if (list != null && list.size() > 0) {
      ar = new BbsVO[list.size()];
      list.toArray(ar);
    }

    return ar;
  }

  public BbsVO get_bbs(String b_idx) {
    return bbsMapper.get_bbs(b_idx);
  }
}
