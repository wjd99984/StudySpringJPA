package com.springjpastudy.service.orderService;


import com.springjpastudy.domain.delivery.Delivery;
import com.springjpastudy.domain.item.Item;
import com.springjpastudy.domain.member.Member;
import com.springjpastudy.domain.order.Order;
import com.springjpastudy.domain.oredrItem.orderItem;
import com.springjpastudy.excetion.NotEnoughException;
import com.springjpastudy.repository.itemRepository.ItemRepository;
import com.springjpastudy.repository.memberRepostiory.MemberRepository;
import com.springjpastudy.repository.orderRepository.OrderRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.springjpastudy.domain.oredrItem.orderItem.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository ItemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {


        //엔티티 조회
        Member member = memberRepository.findById(memberId);
        Item item = ItemRepository.findById(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        //다른곳에서 많이쓸대는 ㄴㄴ 별도로 뺴서 사용
        orderItem orderItem = createOrderItem(count, item, item.getPrice());

        // 주문 생성
        Order order = Order.createOrder(member, delivery,orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();

    }

    //주문취소
    @Transactional
    public void cancelOrder(long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }



    // 주문 검색
   /* public List<Order> findOrders(Long orderId) {
        return orderRepository.findAll();
    }*/
}
