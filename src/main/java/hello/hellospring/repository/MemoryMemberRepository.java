package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
//        store.get(id);  // 이렇게 할 경우 null 이 발생할 수 있다.
        return Optional.ofNullable(store.get(id)); // 이렇게 하는 것이 null 처리하는 방법

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 루프를 돌면서 하나를 찾으면 반환, 없으면 Optional 통해 null 로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
