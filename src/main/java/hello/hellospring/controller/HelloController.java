package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹에서 헬로우를 클릭하면 이 메서드를 호출해준다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";

    }
}
