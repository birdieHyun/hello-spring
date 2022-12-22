package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.*;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    // 중요한 내용이다! 그럼 우테코 테스트케이스는 뭐야...


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

//        System.out.println("result = " + (result == member));   이렇게 글자로 계속 볼 수는 없기 때문에  Assert 라는 기능을 사용

//        Assertions.assertEquals(member, result);  junit 사용

        assertThat(member).isEqualTo(result);   // assertj 사용
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        Optional<Member> result = repository.findByName("spring1");
        Member result = repository.findByName("spring1").get(); // get 을 쓰면 optional 뺄 수 있다.
        Member result2 = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
