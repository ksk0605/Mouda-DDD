package com.mouda.api.moim.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mouda.api.moim.domain.Member;
import com.mouda.api.moim.domain.Moim;
import com.mouda.api.moim.domain.MoimRepository;
import com.mouda.api.moim.domain.MoimRole;
import com.mouda.api.moim.domain.Moims;
import com.mouda.api.moim.domain.Participant;
import com.mouda.api.moim.domain.Participants;
import com.mouda.api.jpa.entity.MemberEntity;
import com.mouda.api.jpa.entity.MoimEntity;
import com.mouda.api.jpa.entity.MoimMemberEntity;
import com.mouda.api.jpa.repository.MemberJpaRepository;
import com.mouda.api.jpa.repository.MoimJpaRepository;
import com.mouda.api.jpa.repository.MoimMemberJpaRepository;

@Repository
public class MoimCoreRepository implements MoimRepository {

	private final MoimJpaRepository moimJpaRepository;
	private final MemberJpaRepository memberJpaRepository;
	private final MoimMemberJpaRepository moimMemberJpaRepository;

	public MoimCoreRepository(
		MoimJpaRepository moimJpaRepository,
		MemberJpaRepository memberJpaRepository,
		MoimMemberJpaRepository moimMemberJpaRepository
	) {
		this.moimJpaRepository = moimJpaRepository;
		this.memberJpaRepository = memberJpaRepository;
		this.moimMemberJpaRepository = moimMemberJpaRepository;
	}

	@Override
	public long append(Moim moim, Member member) {
		MoimEntity moimEntity = MoimEntity.from(moim);
		MoimEntity savedMoimEntityId = moimJpaRepository.save(moimEntity);

		MoimMemberEntity moimMemberEntity = new MoimMemberEntity(moimEntity.getId(), member.getId(), moim.findMoimRole(member));
		moimMemberJpaRepository.save(moimMemberEntity);

		return savedMoimEntityId.getId();
	}

	@Override
	public long update(Moim moim, Member member) {
		MoimMemberEntity moimMemberEntity = new MoimMemberEntity(moim.getId(), member.getId(), moim.findMoimRole(member));
		MoimMemberEntity savedMoimMemberEntity = moimMemberJpaRepository.save(moimMemberEntity);

		return savedMoimMemberEntity.getMoimId();
	}

	@Override
	public Moims findAll() {
		List<MoimEntity> moimEntities = moimJpaRepository.findAll();

		List<Moim> moims = moimEntities.stream()
			.map(MoimEntity::toMoim)
			.toList();

		return new Moims(moims);
	}

	@Override
	public Moim findById(long moimId) {
		MoimEntity moimEntity = moimJpaRepository.findById(moimId)
			.orElseThrow(() -> new IllegalArgumentException("No such moim entity has moimId : " + moimId));

		List<MoimMemberEntity> moimMemberEntities = moimMemberJpaRepository.findByMoimId(moimId);

		return Moim.create(
			moimEntity.getId(),
			moimEntity.getName(),
			moimEntity.getDate(),
			moimEntity.getTime(),
			moimEntity.getDescription(),
			moimEntity.getMaxParticipants(),
			getParticipants(moimMemberEntities)
		);
	}

	private Participants getParticipants(List<MoimMemberEntity> moimMemberEntities) {
		List<Participant> participants = new ArrayList<>();

		for (MoimMemberEntity moimMemberEntity : moimMemberEntities) {
			MemberEntity memberEntity = memberJpaRepository.readById(moimMemberEntity.getMemberId());
			Member member = memberEntity.toMember();
			participants.add(new Participant(member, MoimRole.from(moimMemberEntity.getMoimRole())));
		}
		return new Participants(participants);
	}
}
