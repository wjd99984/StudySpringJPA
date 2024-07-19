package com.springjpastudy.repository.memberRepostiory;

import com.springjpastudy.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

    //회원 기능
@Repository
public class MemberRepository  {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
    return em.createQuery("select m from Member m" ,Member.class).getResultList();
    }

    //이름으로 조회시
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                 .setParameter("name", name)
                .getResultList();
    }



}
