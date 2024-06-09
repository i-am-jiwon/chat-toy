package toy.jj.chat.domain.member.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}
