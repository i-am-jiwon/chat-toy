package toy.jj.chat.domain.msg.msg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.jj.chat.domain.msg.msg.entity.Msg;

public interface MsgRepository extends JpaRepository<Msg, Long> {
}
