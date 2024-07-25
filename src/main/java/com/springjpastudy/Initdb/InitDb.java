package com.springjpastudy.Initdb;


import com.springjpastudy.domain.address.Address;
import com.springjpastudy.domain.delivery.Delivery;
import com.springjpastudy.domain.item.Book;
import com.springjpastudy.domain.member.Member;
import com.springjpastudy.domain.order.Order;
import com.springjpastudy.domain.oredrItem.orderItem;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {
	private  final  InitService initService;
		@PostConstruct
		public void init() {
			initService.dbInit1();
		}
	
	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {
		private  final EntityManager em;
		public void dbInit1() {
			Member member = new Member();
			member.setName("userA");
			member.setAddress(new Address("서울", "1", "1111"));
			em.persist(member);
			
			Book book1= new Book();
			book1.setName("book1");
			book1.setPrice(10000);
			book1.setStockQuantity(100);
			em.persist(book1);
			
			Book book2 = new Book();
			book2.setName("book2");
			book2.setPrice(10000);
			book2.setStockQuantity(100);
			em.persist(book2);
			
			orderItem orderItem1 = orderItem.createOrderItem(1, book1, 10000);
			orderItem orderItem2 = orderItem.createOrderItem(2, book2, 10000);
			
			Delivery delivery = new Delivery();
			delivery.setAddress(member.getAddress());
			Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
			em.persist(order);
			
			
			
		}
	}
}
