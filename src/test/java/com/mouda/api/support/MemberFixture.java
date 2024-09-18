package com.mouda.api.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouda.api.moim.domain.Member;
import com.mouda.api.jpa.entity.MemberEntity;
import com.mouda.api.jpa.repository.MemberJpaRepository;

@Component
public class MemberFixture {

	@Autowired
	MemberJpaRepository memberJpaRepository;

	public Member create(String name) {
		MemberEntity memberEntity = memberJpaRepository.save(MemberEntity.from(new Member(name)));
		return memberEntity.toMember();
	}
}
