package com.springjpastudy.repository.itemRepository;


import com.springjpastudy.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;


    //상품 저장
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        }else {
            em.merge(item); //업뎃비슷무리함
        }
    }

    // 상품 하나 찾기
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    //상품 전체 리스트
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }


}
