package com.springjpastudy.controller.Item;


import com.springjpastudy.domain.item.Book;
import com.springjpastudy.domain.item.Item;
import com.springjpastudy.service.itemService.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("items/new")
    public String crate(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        return "items/ItemForm";
    }


    @PostMapping("items/new")
    public String crateForm(ItemForm form) {

        Book book = new Book();

        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.save(book);
        return "redirect:/";
    }


    @GetMapping("/items")
    public String List(Model model) {
        List<Item> items = itemService.findItem();
        model.addAttribute("items", items);
        return "items/itemList";
    }


    @GetMapping("/items/{itemId}/edit")
    public String update(@PathVariable Long itemId, Model model) {

        Book item = (Book) itemService.findItemById(itemId);

        ItemForm form = new ItemForm();
        form.setId(itemId);
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/UpdateItemForm";

    }


    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") ItemForm form) {

        Book book = new Book();

        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.save(book);
        return "redirect:/items";

    }
}