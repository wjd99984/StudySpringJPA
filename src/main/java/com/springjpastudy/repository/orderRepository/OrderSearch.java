package com.springjpastudy.repository.orderRepository;

import com.springjpastudy.domain.order.OrderStatue;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class OrderSearch {
    private String memberName; // 회원이름
    private OrderStatue orderStatus; //주문상태

}
