package com.mouda.api.moim.domain;

import java.util.Objects;

public class Participant {

	private final Member member;
	private final MoimRole moimRole;

	public Participant(Member member, MoimRole moimRole) {
		this.member = member;
		this.moimRole = moimRole;
	}

	public static Participant toAttendant(Member member) {
		return new Participant(member, MoimRole.ATTENDANT);
	}

	public boolean hasMember(Member member) {
		return this.member.equals(member);
	}

	public boolean isSameAs(Participant newParticipant) {
		return this.equals(newParticipant);
	}

	public long getId() {
		return member.getId();
	}

	public MoimRole getMoimRole() {
		return moimRole;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Participant that = (Participant)o;
		return Objects.equals(member, that.member) && moimRole == that.moimRole;
	}

	@Override
	public int hashCode() {
		return Objects.hash(member, moimRole);
	}
}
