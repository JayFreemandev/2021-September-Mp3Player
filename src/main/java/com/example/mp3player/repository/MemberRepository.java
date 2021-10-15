package com.example.mp3player.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mp3player.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
