package tt.boot_0306.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tt.boot_0306.mapper.BbsMapper;
import tt.boot_0306.service.BbsService;
import tt.boot_0306.util.Paging;
import tt.boot_0306.vo.BbsVO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BbsController {

  @Autowired
  private BbsService bbsService;

  @RequestMapping("/list")
  @ResponseBody
  public Map<String, Object> list(@RequestParam String bname,
                                  String searchType, String searchValue,
                                  String cPage) {
    Map<String, Object> map = new HashMap<>();

    int nowPage = 1; // 먼저 기본페이지를 1로 설정
    if (cPage != null) { // 현재 페이지가 없다면 현재 페이지를 nowPage로
      nowPage = Integer.parseInt(cPage);
    }
    // bname이 무조건 인자로 넘어와야 한다. 그런데 공백이면 안된다.
    if (bname.trim().length() == 0) {
      bname = "BBS";
    }

    // 전체게시물의 수
    int totalRecord = bbsService.getTotalCount(bname, searchType, searchValue);

    // 페이징 객체 생성
    // 한 페이지 당 보여질 게시물의 수 : 7, 한 블럭당 표현할 페이지 수 : 5
    Paging page = new Paging(7, 5);
    page.setTotalRecord(totalRecord);
    page.setNowPage(nowPage);

    // 뷰페이지에서 표현할 목록을 가져올 때 사용하는 값(begin, end)
    int begin = page.getBegin();
    int end = page.getEnd();

    // 목록 가져오기
    BbsVO[] ar = bbsService.getList(bname, searchType, searchValue, begin, end);

    // 뷰페이지에서 표현할 정보들을 mv에 저장
    map.put("ar", ar);
    map.put("page", page);
    map.put("totalRecord", totalRecord);
    map.put("bname", bname);
    map.put("nowPage", nowPage);
    map.put("totalPage", page.getTotalPage()); // 전체 페이지 수
    map.put("cPage", cPage);

    return map;
  }

  @RequestMapping("/getBbs")
  @ResponseBody
  public Map<String, Object> getBbs(@RequestParam String b_idx) {
    Map<String, Object> map = new HashMap<>();

    BbsVO vo = bbsService.get_bbs(b_idx);
    map.put("vo", vo);

    return map;
  }

}
