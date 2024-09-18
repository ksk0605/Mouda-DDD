package com.mouda.api.moim.domain;

public record MoimSpec(
	String moimName,
	String moimDate,
	String moimTime,
	String moimDescription,
	String hostMemberName,
	long hostMemberId
) {
	public Moim toMoim() {
		Member hostMember = new Member(hostMemberId, hostMemberName);
		return null;
	}
}
