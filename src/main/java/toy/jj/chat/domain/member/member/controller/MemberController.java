package toy.jj.chat.domain.member.member.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.jj.chat.domain.member.member.entity.Member;
import toy.jj.chat.domain.member.member.service.MemberService;
import toy.jj.chat.global.rsData.RsData;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    public record SignupRequestBody(@NotBlank String username, @NotBlank String password1, @NotBlank String password2) {
    }

    @PostMapping("/signup")
    public RsData<Member> signup(@Valid @RequestBody SignupRequestBody body) {
        if (!body.password1.equals(body.password2))
            throw new RuntimeException("400-1");

        if (memberService.findByUsername(body.username).isPresent())
            throw new RuntimeException("400-2");

        return memberService.join(body.username, body.password1);
    }
}
