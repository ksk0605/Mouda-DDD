package com.mouda.api.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mouda.api.jpa.entity.MemberEntity;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

	MemberEntity readById(Long memberId);
}
