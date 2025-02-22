package tt.lom1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Control {
  @RequestMapping("/")
  public ModelAndView index() {
    EVO evo = new EVO();
    evo.setEmpno("1000");
    evo.setEname("ilwoo");
    System.out.println(evo.getEmpno());
    System.out.println(evo.getEname());
    ModelAndView mv = new ModelAndView("index");
    return mv;
  }
}
