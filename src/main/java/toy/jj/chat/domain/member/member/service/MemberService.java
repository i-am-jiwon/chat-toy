package toy.jj.chat.domain.member.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.jj.chat.domain.member.member.entity.Member;
import toy.jj.chat.domain.member.member.repository.MemberRepository;
import toy.jj.chat.global.rsData.RsData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<Member> join(String username, String password) {
        Member member = Member.builder()
                .nickname(username)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        memberRepository.save(member);

        return RsData.of("%s님 환영합니다. 회원가입이 완료되었습니다. 로그인 후 이용해주세요.".formatted(member.getUsername()), member);
    }


    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}