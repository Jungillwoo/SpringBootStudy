package com.example.bbs_0220.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.example.bbs_0220.service.BbsService;
import com.example.bbs_0220.util.FileRenameUtil;
import com.example.bbs_0220.util.Paging;
import com.example.bbs_0220.vo.BbsVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.core.io.Resource;




@Controller
public class BbsController {

    @Autowired
    private BbsService bbsService;

    @Autowired
    private ServletContext application;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession session;

    @Value("${server.upload.path}")
    private String upload_img;

    @Value("${server.bbs_upload.path}")
    private String bbs_upload;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam String bname, String searchType, String searchValue,
                            String cPage) {
        ModelAndView mv = new ModelAndView();

        int nowPage = 1;
        if (cPage != null) {
            nowPage = Integer.parseInt(cPage);
        }
        // bname이 무조건 인자로 넘어와야 한다. 그런데 공백이면 안된다.
        if (bname.trim().length() == 0)
            bname = "BBS";

        // 전체게시물의 수
        int totalRecord = bbsService.getTotalCount(searchType, searchValue, bname);
        
        // 페이징 객체 생성
        Paging page = new Paging(7, 5);
        page.setTotalRecord(totalRecord);
        page.setNowPage(nowPage);

        // 뷰페이지에서 표현할 목록을 가져올 때 사용하는 값(begin, end)
        int begin = page.getBegin();
        int end = page.getEnd();

        // 목록 가져오기
        BbsVO[] ar = bbsService.getList(searchType, searchValue, bname, begin, end);

        // 뷰페이지에서 표현할 정보들을 mv에 저장
        mv.addObject("ar", ar);
        mv.addObject("page", page);
        mv.addObject("totalRecord", totalRecord);
        mv.addObject("bname", bname);
        mv.addObject("nowPage", nowPage);

        mv.setViewName(bname+"/list");

        return mv;
    }

    @GetMapping("/write")
    public ModelAndView write(@RequestParam String bname, String cPage) {
        ModelAndView mv = new ModelAndView(bname+"/write");
        return mv;
    }

    @PostMapping("/write")
    public ModelAndView write(BbsVO vo) {
        ModelAndView mv = new ModelAndView();

        // 전달되어오는 파라미터 값들은 모두 vo에 저장된 상태다.
        // 중요한 것은 첨부파일이 있는지 없는지 확인
        MultipartFile  mf = vo.getFile();

        // 파일이 첨부되지 않았다고 해도 mf는 null이 아니며 용량이 0이다.
        if (mf.getSize() > 0) {
            // 파일이 첨부된 상태일 때만 서버에 업로드한다.

            // 업로드할 서버의 위치를 절대경로화 시킨다.
            String realPath = application.getRealPath(bbs_upload);

            String oname = mf.getOriginalFilename();

            // 같은 파일명이 있다면 파일명 변경
            String fname = FileRenameUtil.checkSameFileName(oname, realPath);

            try {
                mf.transferTo(new File(realPath, fname)); // 업로드
            } catch (Exception e) {
                e.printStackTrace();
            }

            // DB에 저장될 파일관련 정보(file_name, ori_name) 지정
            vo.setFile_name(fname);
            vo.setOri_name(oname);
        }
        vo.setIp(request.getRemoteAddr()); // ip 지정

        // DB에 저장
        int cnt = bbsService.addBbs(vo);

        mv.setViewName("redirect:/list?bname=" + vo.getBname());

        return mv;
    }
    

    @PostMapping("saveImg")
    @ResponseBody
    public Map<String, String> saveImg(MultipartFile upload, String bname) {
        Map<String, String> map = new HashMap<String, String>();
        if (upload.getSize() > 0) {
            // 파일을 저장할 위치(upload_img) 를 절대경로화 시켜야한다.
            String realPath = application.getRealPath(upload_img);

            // 파일명을 얻어낸다.
            String oname = upload.getOriginalFilename();

            // 동일한 파일명이 있을 때만 파일명을 변경한다.
            String fname = FileRenameUtil.checkSameFileName(oname, realPath);

            try {
                upload.transferTo(new File(realPath, fname)); // 업로드
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 업로드된 파일의 경로를 반환하기 위해 현재 서버의 URL을 알아낸다.
            String url_path = request.getContextPath();

            // JSON으로 반환하기 위해 map 에 저장
            map.put("url", url_path + upload_img);
            map.put("fname", fname);
        }

        return map;
    }
    
    @RequestMapping("/view")
    public ModelAndView requestMethodName(@RequestParam String b_idx, String bname, String cPage) {
        ModelAndView mv = new ModelAndView();

        // 세션에 read_list라는 이름으로 저장된 객체를 얻어낸다.
        Object obj = session.getAttribute("read_list");
        ArrayList<BbsVO> list = null;

        // obj가 null이 아니면 obj를 형변환하여 list에 저장
        if (obj != null) {
            list = (ArrayList<BbsVO>) obj;
        } else {
            list = new ArrayList<BbsVO>();
            // 새로 생성된 경우일때만 session에 저장
            session.setAttribute("read_list", list);
        }
        // 사용자가 선택한 게시물의 기본키를 인자로 받았으니 그것을 조건으로 해당 게시물을 객체로 얻어낸다.

        BbsVO vo = bbsService.getBbs(b_idx);

        // 이미 읽었던 게시물인지 아닌지 확인
        boolean chk = false;
        for (BbsVO bvo : list) {
            if (bvo.getB_idx().equalsIgnoreCase(b_idx)) { // 대소문자 구별 없이 string 값 비교
                chk = true;
                break;
            }
        }

        // chk 가 false를 유지하고 있다면 한번도 읽지 않은 게시물을 선택한 경우다.
        if (!chk) {
            // 화면에 즉각적으로 반영하기 위해 hit값을 얻어낸다.
            int hit = Integer.parseInt(vo.getHit());
            vo.setHit(String.valueOf(hit + 1));

            // DB에서 hit수 증가
            bbsService.hit(b_idx);

            list.add(vo); // 지역변수인 list가 사실은 session에 "read_list" 라는 이름으로
                          // 저장된 리스트 구조를 가리키고 있는 상태이므로 session의 리스트에 추가된다.
        }

        mv.addObject("vo", vo);
        mv.addObject("bname", bname);
        mv.addObject("cPage", cPage);
        mv.setViewName(bname + "/view");

        return mv;
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam String f_name) {
        // 파일들이 저장되어 있는 곳(bbs_upload)를 절대경로화 시킨다.
        String realPath = application.getRealPath(bbs_upload + "/" + f_name);
        File f = new File(realPath);

        // 존재여부 확인
        if (f.exists()) {
            // 존재할 경우에만 요청한 곳으로 파일을 보내줘야 한다.
            byte[] buf = null;
            int size = -1;

            // 파일 다운로드에 필요한 스트림 준비
            BufferedInputStream bis = null;
            FileInputStream fis = null;

            // 요청한 곳으로 보낼 스트림 준비
            BufferedOutputStream bos = null;
            ServletOutputStream sos = null; // 응답을 하는 것이 접속자의 컴퓨터로 다운로드를 시켜야 하기 때문에
                                            // response를 통해 OutputStream을 얻어내야 한다. 이때 
                                            // response가 주는 스트림이 ServletOutputStream만 줄 수 있기 때문에
                                            // sos 를 선언한다.
            try {
                // 접속자 화면에 다운로드 창을 보여준다.
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment;filename=" + 
                    new String(f_name.getBytes(), "8859_1"));
                
                // 다운로드 할 파일과 연결되는 스트림 생성
                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);
                
                // response를 통해 이미 out이라는 스트림이 존재한다.
                // 다운로드를 시키기 위해 스트림을 준비한다.
                sos = response.getOutputStream();
                bos = new BufferedOutputStream(sos);

                while ((size = bis.read(buf)) != -1)   {
                    // 읽은 자원을 buf에 적재된 상태다. 이제는 buf라는 배열에 있는 자원들을 쓰기해서 사용자에게 보낸다.
                    bos.write(buf, 0, size);
                    bos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }    
                    if (sos != null) {
                        sos.close();
                    }
                    if (bos != null) {
                        bos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }                
        }

        return null;
    }
    
}
