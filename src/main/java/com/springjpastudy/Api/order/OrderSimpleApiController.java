package com.springjpastudy.Api.order;


import com.springjpastudy.domain.address.Address;
import com.springjpastudy.domain.order.Order;
import com.springjpastudy.domain.order.OrderStatue;
import com.springjpastudy.repository.orderRepository.OrderRepository;
import com.springjpastudy.repository.orderRepository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

	private final OrderRepository orderRepository;
	
	/*@GetMapping("/api/v1/simple")
	public List<Order> getOrderSimple(){
		List<Order> order = orderRepository.findAllByString(new OrderSearch());
		return order;
		
	}
	*/
	
	@GetMapping("api/v2/simple")
	public List<simple> orderv2() {
		List<Order> orders  =orderRepository.findAllByString(new OrderSearch());
		List<simple> result = orders.stream()
				.map(o -> new simple(o))
				.collect(Collectors.toList());
		return result;
		
	}
	
	@Data
	static class simple {
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatue orderStatus;
		private Address address;
		
		
		public simple(Order order) {
			orderId = order.getId();
			name= order.getMember().getName();
			orderDate = order.getOrderDate();
			orderStatus = order.getStatue();
			address=order.getDelivery().getAddress();
			
		}
	}
}
