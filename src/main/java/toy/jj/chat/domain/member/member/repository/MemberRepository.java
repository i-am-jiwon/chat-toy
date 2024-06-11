package toy.jj.chat.domain.member.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.jj.chat.domain.member.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByRefreshToken(String refreshToken);

}
