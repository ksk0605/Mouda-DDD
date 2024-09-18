package com.mouda.api.moim.domain;

import java.util.Arrays;

public enum MoimRole {
	HOST, ATTENDANT;

	public static MoimRole from(String role) {
		return Arrays.stream(values())
			.filter(moimRole -> moimRole.name().equals(role))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("No such moim role(moimRole : " + role + ")"));
	}
}
