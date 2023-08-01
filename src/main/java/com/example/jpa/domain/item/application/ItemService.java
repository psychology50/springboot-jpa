package com.example.jpa.domain.item.application;

import com.example.jpa.domain.item.dao.ItemRepository;
import com.example.jpa.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOneById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 존재하지 않습니다.")
        );
    }
}
