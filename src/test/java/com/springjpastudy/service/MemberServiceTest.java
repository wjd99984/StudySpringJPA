package com.springjpastudy.service;

import com.springjpastudy.domain.member.Member;
import com.springjpastudy.repository.memberRepostiory.MemberRepository;
import com.springjpastudy.service.memberService.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  //기본적으로 롤백을함
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void 회원가입 () throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");
        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals(member, memberRepository.findById(saveId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //When
        memberService.join(member1);
        //Then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void findOne() throws Exception {
        //given


        //when


        //then
    }
}