package com.mouda.api.moim.service;

import org.springframework.stereotype.Service;

import com.mouda.api.moim.domain.Member;
import com.mouda.api.moim.domain.Moim;
import com.mouda.api.moim.domain.MoimRepository;
import com.mouda.api.moim.domain.Moims;
import com.mouda.api.moim.domain.Participant;

@Service
public class MoimService {

	private final MoimRepository moimRepository;

	public MoimService(MoimRepository moimRepository) {
		this.moimRepository = moimRepository;
	}

	public long createMoim(Moim moim, Member member) {
		Moims moims = moimRepository.findAll();

		moim.validate();
		moims.validateExistMoimName(moim);

		Participant host = Participant.toAttendant(member);
		moim.enter(host);

		return moimRepository.append(moim, member);
	}

	public long enterMoim(long moimId, Member member) {
		Moim moim = moimRepository.findById(moimId);

		Participant attendant = Participant.toAttendant(member);
		moim.enter(attendant);

		return moimRepository.update(moim, member);
	}
}
