package com.springjpastudy.service.orderService;

import com.springjpastudy.domain.address.Address;
import com.springjpastudy.domain.item.Book;
import com.springjpastudy.domain.item.Item;
import com.springjpastudy.domain.member.Member;
import com.springjpastudy.domain.order.Order;
import com.springjpastudy.domain.order.OrderStatue;
import com.springjpastudy.excetion.NotEnoughException;
import com.springjpastudy.repository.orderRepository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional(readOnly = true)
class OrderServiceTest {

    @Autowired
      EntityManager em;

    @Autowired
     OrderService orderService;

    @Autowired  OrderRepository orderRepository;

    @Test
    void order() {
        Member member = createMember();

        Book book = createbook("늑대",10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        Order getOrder = orderRepository.findById(orderId);

        assertEquals(OrderStatue.ORDER, getOrder.getStatue(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야한다 ");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격 *수량");
        assertEquals(8, book.getStockQuantity(),"주문 수량만큼 재고가 줄어야한다 ");
    }


    @Test
    void 상품주문_재공수량초과() {
        Member member = createMember();
        Item item = createbook("늑대",10000,10);

        int orderCount = 11;

        orderService.order(member.getId(), item.getId(), orderCount);

        NotEnoughException exception = assertThrows(NotEnoughException.class, ()
                -> orderService.order(member.getId(), item.getId(), orderCount));

        assertEquals("재고부족입니다", exception.getMessage());
    }

    @Test
    void 주문취소() {
        Member member = createMember();
        Book item= createbook("늑대",10000,10);

        int orderCount = 10;

        Long orderId=orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findById(orderId);

        assertEquals(OrderStatue.CANCEL, getOrder.getStatue(),"주문 취소시 상태는 CANCEL 이다");
        assertEquals(10, item.getStockQuantity(),"주문이 취소된 상품은 그만큼 재고가 증가 해야함 ");

    }



















    private Book createbook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("앙");
        member.setAddress(new Address("서울 ", "둥", "123-123"));
        em.persist(member);
        return member;
    }
}