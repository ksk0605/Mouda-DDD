package com.mouda.api.moim.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MoimDetail {

	private final String name;
	private final LocalDate date;
	private final LocalTime time;
	private final String description;
	private final int maxParticipants;

	public MoimDetail(String name, LocalDate date, LocalTime time, String description, int maxParticipants) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.description = description;
		this.maxParticipants = maxParticipants;
	}

	public boolean hasSameNameWith(MoimDetail newDetail) {
		return this.name.equals(newDetail.name);
	}

	public void validateDateTime() {
		if (LocalDateTime.of(date, time).isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Moim dateTime is before now");
		}
	}

	public String getName() {
		return name;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public String getDescription() {
		return description;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}
}
