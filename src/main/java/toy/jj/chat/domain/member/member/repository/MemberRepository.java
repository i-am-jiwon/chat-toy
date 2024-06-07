package toy.jj.chat.domain.member.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.jj.chat.domain.member.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
