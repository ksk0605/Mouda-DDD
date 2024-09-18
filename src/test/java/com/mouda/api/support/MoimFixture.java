package com.mouda.api.support;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.mouda.api.moim.domain.Member;
import com.mouda.api.moim.domain.Moim;
import com.mouda.api.moim.domain.MoimDetail;
import com.mouda.api.moim.domain.MoimRole;
import com.mouda.api.moim.domain.Participant;
import com.mouda.api.moim.domain.Participants;

public class MoimFixture {

	public static Moim create(Member member, String name) {
		MoimDetail moimDetail = new MoimDetail(name, LocalDate.now().plusDays(2), LocalTime.now(),
			"this is test moim", 10);
		Participant host = new Participant(member, MoimRole.HOST);
		List<Participant> participants = new ArrayList<>();
		participants.add(host);
		return new Moim(moimDetail, new Participants(participants));
	}

	public static Moim create(Member member, String name, LocalDate date) {
		MoimDetail moimDetail = new MoimDetail(name, date, LocalTime.now(),
			"this is test moim", 10);
		Participant host = new Participant(member, MoimRole.HOST);
		List<Participant> participants = new ArrayList<>();
		participants.add(host);
		return new Moim(moimDetail, new Participants(participants));
	}
}
