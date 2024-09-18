package com.mouda.api.moim.domain;

import java.util.List;

public class Moims {

	private final List<Moim> moims;

	public Moims(List<Moim> moims) {
		this.moims = moims;
	}

	public void validateExistMoimName(Moim newMoim) {
		for (Moim moim : moims) {
			if (moim.hasSameNameWith(newMoim)) {
				throw new IllegalArgumentException("Moim name already exist");
			}
		}
	}

	public int getSize() {
		return moims.size();
	}
}
