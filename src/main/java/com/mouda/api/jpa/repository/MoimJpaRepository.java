package com.mouda.api.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mouda.api.jpa.entity.MoimEntity;

public interface MoimJpaRepository extends JpaRepository<MoimEntity, Long> {
}
