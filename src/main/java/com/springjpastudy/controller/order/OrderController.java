package com.springjpastudy.controller.order;


import com.springjpastudy.domain.item.Item;
import com.springjpastudy.domain.member.Member;
import com.springjpastudy.domain.order.Order;
import com.springjpastudy.repository.orderRepository.OrderSearch;
import com.springjpastudy.service.itemService.ItemService;
import com.springjpastudy.service.memberService.MemberService;
import com.springjpastudy.service.orderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {


    private final MemberService memberService;
    private final OrderService orderService;
    private final ItemService itemService;


    @GetMapping("/order")
    public String crateForm( Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItem();
        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "/order/orderForm";
    }

    @PostMapping("/orders")
    public String order(
            @RequestParam("memberId") Long memberId,
            @RequestParam("itemId") Long itemId,
            @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "/order/orderList";

    }

    @PostMapping("/order/{orderId}/cancal")
    public String cancel(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}
