package com.springjpastudy.service.memberService;

import com.springjpastudy.domain.member.Member;
import com.springjpastudy.repository.memberRepostiory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// 회원 기능
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // 파이널 필드 가지고 생성자 만들어줌  이걸쓰자잉 롬북 쓸떄
public class MemberService {

    private final MemberRepository memberRepository;


    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName());//데이터 베이스에 네임을 유니크 제약 조건으로 잡는것을 권장
        if (!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    // 하나의 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
