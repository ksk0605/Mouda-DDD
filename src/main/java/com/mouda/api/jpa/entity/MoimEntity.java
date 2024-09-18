package com.mouda.api.jpa.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.mouda.api.moim.domain.Moim;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "moim")
@Getter
public class MoimEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate date;
	private LocalTime time;
	private String description;
	private int maxParticipants;

	protected MoimEntity() {
	}

	public MoimEntity(String name, LocalDate date, LocalTime time, String description, int maxParticipants) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.description = description;
		this.maxParticipants = maxParticipants;
	}

	public static MoimEntity from(Moim moim) {
		return new MoimEntity(
			moim.getName(),
			moim.getDate(),
			moim.getTime(),
			moim.getDescription(),
			moim.getMaxParticipants()
		);
	}

	public Moim toMoim() {
		return Moim.createWithEmptyParticipants(
			id,
			name,
			date,
			time,
			description,
			maxParticipants
		);
	}
}
