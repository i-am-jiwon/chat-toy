package toy.jj.chat.global.security;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import toy.jj.chat.domain.member.member.entity.Member;
import toy.jj.chat.domain.member.member.service.AuthTokenService;
import toy.jj.chat.domain.member.member.service.MemberService;
import toy.jj.chat.global.rq.Rq;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Component
@RequiredArgsConstructor
public class ASuccessHandler implements AuthenticationSuccessHandler {

    private final AuthTokenService authTokenService;
    private final MemberService memberService;
    private final Rq rq;

    @Value("localhost:3000")
    private String frontUrl;

    public String extractDomainFromFrontUrl(String frontUrl) {
        URI uri = null;
        try {
            uri = new URI(frontUrl);
        }catch (URISyntaxException e){
            e.printStackTrace();
        }

        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Member member = memberService.findByUsername(authentication.getName()).get();
        String accessToken = authTokenService.genAccessToken(member);
        String refreshToken = member.getRefreshToken();

        String redirectUrlAfterSocialLogin = rq.getCookieValue("redirectUrlAfterSocialLogin", "");
        String domain = extractDomainFromFrontUrl(frontUrl);

        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", accessToken)
                .path("/")
                .domain(domain)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                .path("/")
                .domain(domain)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .build();

        response.addHeader("Set-Cookie", accessTokenCookie.toString());
        response.addHeader("Set-Cookie", refreshTokenCookie.toString());


        Cookie[] cookies = request.getCookies();

        Cookie cookie1 = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(redirectUrlAfterSocialLogin)) {
                cookie1 = cookie;
            }
        }


        if (cookie1 != null) {
            cookie1.setPath("/");
            cookie1.setMaxAge(0);
            response.addCookie(cookie1);
        }

        String userName = member.getUsername();
        String redirectUrlWithUsername = frontUrl + "?kakaousername=" + URLEncoder.encode(userName, "UTF-8");

        response.sendRedirect(redirectUrlWithUsername);
    }
}