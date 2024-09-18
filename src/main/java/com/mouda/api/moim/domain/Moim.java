package com.mouda.api.moim.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Moim {

	private Long id;
	private final MoimDetail moimDetail;
	private final Participants participants;

	public Moim(MoimDetail moimDetail, Participants participants) {
		this.moimDetail = moimDetail;
		this.participants = participants;
	}

	private Moim(long id, MoimDetail moimDetail, Participants participants) {
		this.id = id;
		this.moimDetail = moimDetail;
		this.participants = participants;
	}

	public static Moim createWithEmptyParticipants(
		Long id,
		String name,
		LocalDate date,
		LocalTime time,
		String description,
		int maxParticipants
	) {
		MoimDetail newMoimDetail = new MoimDetail(name, date, time, description, maxParticipants);

		return new Moim(
			id,
			newMoimDetail,
			Participants.empty()
		);
	}

	public static Moim create(
		Long id,
		String name,
		LocalDate date,
		LocalTime time,
		String description,
		int maxParticipants,
		Participants participants
	) {
		MoimDetail newMoimDetail = new MoimDetail(name, date, time, description, maxParticipants);

		return new Moim(
			id,
			newMoimDetail,
			participants
		);
	}

	public void validate() {
		moimDetail.validateDateTime();
	}

	public boolean hasSameNameWith(Moim newMoim) {
		return moimDetail.hasSameNameWith(newMoim.moimDetail);
	}

	public void enter(Participant newParticipant) {
		participants.add(newParticipant);
	}

	public String findMoimRole(Member member) {
		return participants.findMoimRole(member).name();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return moimDetail.getName();
	}

	public LocalDate getDate() {
		return moimDetail.getDate();
	}

	public LocalTime getTime() {
		return moimDetail.getTime();
	}

	public String getDescription() {
		return moimDetail.getDescription();
	}

	public int getMaxParticipants() {
		return moimDetail.getMaxParticipants();
	}
}
