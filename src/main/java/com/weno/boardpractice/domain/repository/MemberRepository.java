package com.weno.boardpractice.domain.repository;

import com.weno.boardpractice.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByEmail(String userEmail);
}
