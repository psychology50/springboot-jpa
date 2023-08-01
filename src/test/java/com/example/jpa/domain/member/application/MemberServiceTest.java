package com.example.jpa.domain.member.application;

import com.example.jpa.domain.member.dao.MemberRepository;
import com.example.jpa.domain.member.domain.Member;
import com.example.jpa.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = createMemberEntity();
    }

    @Test
    public void register() throws Exception {
        // given
        setMemberId(member);
        given(memberRepository.save(any(Member.class))).willReturn(member);

        // when
        Member savedMember = memberService.join(member);

        // then
        assertEquals(member, savedMember);
    }

    @Test
    public void duplicatedMember() throws Exception {
        // given
        given(memberRepository.existsByName(anyString())).willReturn(true);

        // when
        Member savedMember = memberService.join(member);

        // then
        assertEquals("", savedMember.getName());
    }

    private Member createMemberEntity() {
        return Member.of("kim", Address.of("seoul", "gangnam", "12345"));
    }

    private void setMemberId(Member member) {
        Long fakeMemberId = 1L;
        ReflectionTestUtils.setField(member, "id", fakeMemberId);
    }
}