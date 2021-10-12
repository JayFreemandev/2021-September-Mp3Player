package com.example.mp3player.repository;

import com.example.mp3player.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers(){
        IntStream.range(1, 10).forEach(i -> {
            Member member = Member.builder()
                    .email("user"+i+"@gmail.com")
                    .nickname("USER"+i)
                    .build();

            memberRepository.save(member);
        });
    }
}
