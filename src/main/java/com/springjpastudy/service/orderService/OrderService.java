package com.springjpastudy.service.orderService;


import com.springjpastudy.domain.delivery.Delivery;
import com.springjpastudy.domain.item.Item;
import com.springjpastudy.domain.member.Member;
import com.springjpastudy.repository.itemRepository.ItemRepository;
import com.springjpastudy.repository.memberRepostiory.MemberRepository;
import com.springjpastudy.repository.orderRepository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository ItemRepository;



    //주문

    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 조회
        Member member = memberRepository.findById(memberId);
        Item item = ItemRepository.findById(itemId);
        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

    }
}
