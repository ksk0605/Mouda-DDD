package com.mouda.api.moim.domain;

import java.util.Objects;

public class Member {

	private long id;
	private final String name;

	public Member(String name) {
		this.name = name;
	}

	public Member(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Member member = (Member)o;
		return id == member.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
