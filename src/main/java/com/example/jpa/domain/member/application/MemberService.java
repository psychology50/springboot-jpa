package com.example.jpa.domain.member.application;

import com.example.jpa.domain.member.dao.MemberRepository;
import com.example.jpa.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(Member member) {
        return (isDuplicatedName(member))
                ? Member.empty()
                : memberRepository.save(member);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    private boolean isDuplicatedName(Member member) {
        return memberRepository.existsByName(member.getName());
    }
}
