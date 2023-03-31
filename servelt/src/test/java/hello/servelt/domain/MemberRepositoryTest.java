package hello.servelt.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() throws Exception {
        //given
        Member member1 = new Member("user1", 20);
        Member member2 = new Member("user2", 30);
        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> all = memberRepository.findAll();
        //then
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(member1, member2);

    }
}