package training.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayyHello() {
        return "Hello Spring webMVC " + LocalDateTime.now();
    }
}
