package toy.jj.chat.domain.member.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import toy.jj.chat.global.jpa.entity.BaseTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
public class Member extends BaseTime {
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String refreshToken;
    // 캐시 데이터
    @Transient
    private Boolean _isAdmin;


    private String nickname;



}