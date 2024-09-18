package com.mouda.api.moim.domain;

public interface MoimRepository {

	long append(Moim moim, Member member);

	long update(Moim moim, Member member);

	Moims findAll();

	Moim findById(long moimId);
}
