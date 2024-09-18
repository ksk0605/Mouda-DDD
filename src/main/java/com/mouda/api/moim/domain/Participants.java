package com.mouda.api.moim.domain;

import java.util.List;

public class Participants {

	List<Participant> participants;

	public Participants(List<Participant> participants) {
		this.participants = participants;
	}

	public static Participants empty() {
		return new Participants(List.of());
	}

	public void add(Participant newParticipant) {
		validateAlreadyEnteredMember(newParticipant);
		participants.add(newParticipant);
	}

	private void validateAlreadyEnteredMember(Participant newParticipant) {
		for (Participant participant : participants) {
			if (participant.isSameAs(newParticipant)) {
				throw new IllegalArgumentException("member already entered (id : " + newParticipant.getId() + ")");
			}
		}
	}

	public MoimRole findMoimRole(Member member) {
		for (Participant participant : participants) {
			if (participant.hasMember(member)) {
				return participant.getMoimRole();
			}
		}
		throw new IllegalArgumentException("Cannot find member in moim participants");
	}
}
