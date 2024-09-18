package com.mouda.api.jpa.entity;

import com.mouda.api.moim.domain.Member;
import com.mouda.api.moim.domain.Moim;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "moim_member")
@Getter
public class MoimMemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "moim_id")
	private Long moimId;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "moim_role")
	private String moimRole;

	protected MoimMemberEntity() {
	}

	public MoimMemberEntity(Long moimId, Long memberId, String moimRole) {
		this.moimId = moimId;
		this.memberId = memberId;
		this.moimRole = moimRole;
	}
}
