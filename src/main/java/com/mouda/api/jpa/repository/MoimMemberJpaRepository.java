package com.mouda.api.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mouda.api.jpa.entity.MoimMemberEntity;

public interface MoimMemberJpaRepository extends JpaRepository<MoimMemberEntity, Long> {

	List<MoimMemberEntity> findByMoimId(long moimId);
}
