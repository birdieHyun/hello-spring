package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

//@Repository
public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); // Id 를 기반으로 회원을 찾는다.
    Optional<Member> findByName(String name);
    // 만약 findById 나 Name 으로 회원을 찾았는데 null 인 경우(회원이 없어서), null 대신 Optional 통해 반환 -> java 8에 추가된 기능
    List<Member> findAll();


}
