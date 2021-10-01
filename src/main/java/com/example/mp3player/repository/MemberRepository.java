package com.example.mp3player.repository;

import com.example.mp3player.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{
}
