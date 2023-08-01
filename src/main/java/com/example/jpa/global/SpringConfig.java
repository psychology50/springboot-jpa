package com.example.jpa.global;

import com.example.jpa.domain.item.application.ItemService;
import com.example.jpa.domain.item.dao.ItemRepository;
import com.example.jpa.domain.member.application.MemberService;
import com.example.jpa.domain.member.dao.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository);
    }
}
