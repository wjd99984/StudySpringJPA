package com.springjpastudy.domain.member;


import com.springjpastudy.domain.address.Address;
import com.springjpastudy.domain.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


//실무에서는 겟터는열고 셋터는 꼭필요할때만 사용
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장타입이면
    private Address address;

    @OneToMany(mappedBy = "member") //fk키
    private List<Order> orders = new ArrayList<>();
}

