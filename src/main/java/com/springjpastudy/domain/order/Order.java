package com.springjpastudy.domain.order;


import com.springjpastudy.domain.delivery.Delivery;
import com.springjpastudy.domain.member.Member;
import com.springjpastudy.domain.oredrItem.orderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.naming.Name;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // sql 작성 할때랑 같음 팀플 백엔드보셈
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<orderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrderStatue statue;




}
