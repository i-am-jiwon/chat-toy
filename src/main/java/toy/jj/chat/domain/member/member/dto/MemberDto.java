package toy.jj.chat.domain.member.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import toy.jj.chat.domain.member.member.entity.Member;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
public class MemberDto {
    @NonNull
    private long id;
    @NonNull
    private LocalDateTime createDate;
    @NonNull
    private LocalDateTime modifyDate;
    @NonNull
    private String username;
    @NonNull
    private List<String> authorities;
    @NonNull
    private String nickname;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.createDate = member.getCreatedDate();
        this.modifyDate = member.getModifiedDate();
        this.username = member.getUsername();
        this.authorities = member.getAuthoritiesAsStringList();
        this.nickname = member.getNickname();
    }
}