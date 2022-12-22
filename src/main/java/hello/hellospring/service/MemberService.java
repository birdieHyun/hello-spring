package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
//@Service
public class MemberService {

    private final MemberRepository memberRepository ;

//    @Autowired // 멤버 서비스는 멤버 리파지토리가 필요함 -> 컨테이너에 있는 멤버 레파지토리를 넣어준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    /**
     * 회원가입
     */
    public Long join (Member member) {
        // 같은 이름이 있는 중복 회원 안된다.
//        Optional<Member> result = memberRepository.findByName(member.getName()); // optional 이기 때문에 이와같이 처리할 수 있음
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
//        이렇게 Optional 사용하는 것 보단 밑에와 같이 한번에 코드를 작성하는 것이 더 권장됨 (Optional 사용은 권장되지 않음)

            validateDuplicateMember(member);// 이처럼 로직이 쭉 나오는 경우 메서드로 뽑는 것이 좋다. (컨트롤 t 단축기 -> method 검색)

            memberRepository.save(member);
            return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m ->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
