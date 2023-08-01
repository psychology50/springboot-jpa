package com.example.jpa.domain.item.dao;

import com.example.jpa.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
