package com.example.jwt_0310.domain.bbs.entity.service;

import com.example.jwt_0310.domain.bbs.entity.Bbs;
import com.example.jwt_0310.domain.bbs.entity.repository.BbsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BbsService {

  private final BbsRepository bbsRepository;

  public List<Bbs> getList() {
    return bbsRepository.findAll();
  }

  public Optional<Bbs> getBbs(Long b_idx) {

    // Bbs bbs = null;
    // // Optional 은 (NPE)NullPointException 발생하지 않도록 하는 객체
    // // Optional 을 사용하게 되면 가독성이 높아져 유지보수가 편리해진다.
    // Optional<Bbs> opt = bbsRepository.findById(b_idx);
    // if (!opt.isEmpty()) {
    //   bbs = opt.get();
    // }
    // // OR
    // // if (opt.isPresent()) {
    // //   bbs = opt.get();
    // // }
    // return bbs;

    // return bbsRepository.findById(b_idx).orElse(null); // 위 내용을 한줄로
    return bbsRepository.findById(b_idx);
  }

  // builder 패턴으로 구현해야 @PrePersist 의 내용이 작동한다.
  public Bbs createBbs(String title, String writer, String content) {
    // 밑에 코드 처럼 생성자로 초기화 시 title, writer, content 는 같은 String 형이기 때문에
    // 순서가 뒤바뀌면 오류가 나지는 않지만 값이 잘못 전달되게 된다. 그래서 생긴게 build() 형식이다.
    // Bbs bbs = new Bbs(null, null, title, writer, content);

    // build() 형식으로 초기화 시 순서가 뒤바뀌어도 올바르게 값이 초기화되게 된다.
    Bbs bbs = Bbs.builder().title(title).writer(writer).content(content).build();

    return bbsRepository.save(bbs);
  }

  // builder 패턴으로 구현 안하게 되므로 @PrePersist 의 내용이 작동 불가
  public Bbs createBbs(Bbs bbs) {

    return bbsRepository.save(bbs);
  }
}
