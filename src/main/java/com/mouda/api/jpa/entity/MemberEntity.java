package com.mouda.api.jpa.entity;

import com.mouda.api.moim.domain.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "member")
@Getter
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private MemberEntity(String name) {
		this.name = name;
	}

	public static MemberEntity from(Member member) {
		return new MemberEntity(member.getName());
	}

	public Member toMember() {
		return new Member(
			id,
			name
		);
	}
}
