package com.springjpastudy.domain.oredrItem;


import com.springjpastudy.domain.item.Item;
import com.springjpastudy.domain.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class orderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격
    private int count; //주문수량

    // 비즈니스 로직
    public void cancel() {
        getItem().addStock(count); // 상품수량 원복  
        
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    //생성 매서드
    public  static  orderItem createOrderItem(int count, Item item, int orderPrice) {
        orderItem orderItem = new orderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }
}
