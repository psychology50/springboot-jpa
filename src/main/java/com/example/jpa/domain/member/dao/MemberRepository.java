package com.example.jpa.domain.member.dao;

import com.example.jpa.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
    Member findOne(Long id);
    boolean existsByName(String name);
}
