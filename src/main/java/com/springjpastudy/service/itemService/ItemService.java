package com.springjpastudy.service.itemService;


import com.springjpastudy.domain.item.Book;
import com.springjpastudy.domain.item.Item;
import com.springjpastudy.repository.itemRepository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    // 상품 기능 로직
    private final ItemRepository itemRepository;

    @Transactional //(readOnly = true) 저장이 안됨
    public void save(Item item) {
        itemRepository.save(item);
    }



    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    public Item findItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

}
