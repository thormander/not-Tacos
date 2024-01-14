package nottaco;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller            // <1>
public class HomeController {

  @GetMapping("/")     // <2> Base URL pathing
  public String home() {
    return "home";     // <3>
  }

}