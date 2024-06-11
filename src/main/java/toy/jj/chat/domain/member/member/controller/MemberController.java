package toy.jj.chat.domain.member.member.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.jj.chat.domain.member.member.dto.MemberDto;
import toy.jj.chat.domain.member.member.entity.Member;
import toy.jj.chat.domain.member.member.service.MemberService;
import toy.jj.chat.global.rq.Rq;
import toy.jj.chat.global.rsData.RsData;
import org.springframework.lang.NonNull;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final Rq rq;

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

    public record LoginRequestBody(@NotBlank String username, @NotBlank String password) {
    }

    public record LoginResponseBody(@NonNull MemberDto item) {
    }

    @PostMapping("/login")
    public RsData<LoginResponseBody> login(@Valid @RequestBody LoginRequestBody body) {
        RsData<MemberService.AuthAndMakeTokensResponseBody> authAndMakeTokensRs = memberService.authAndMakeTokens(
                body.username,
                body.password
        );

        rq.setCrossDomainCookie("refreshToken", authAndMakeTokensRs.getData().refreshToken());
        rq.setCrossDomainCookie("accessToken", authAndMakeTokensRs.getData().accessToken());

        RsData<LoginResponseBody> loginResponseBodyRsData = authAndMakeTokensRs.newDataOf(
                new LoginResponseBody(
                        new MemberDto(authAndMakeTokensRs.getData().member())
                )
        );
        return authAndMakeTokensRs.newDataOf(
                new LoginResponseBody(
                        new MemberDto(authAndMakeTokensRs.getData().member())
                )
        );
    }
}
