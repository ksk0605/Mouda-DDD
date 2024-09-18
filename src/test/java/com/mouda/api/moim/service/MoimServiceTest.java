package com.mouda.api.moim.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mouda.api.jpa.entity.MoimMemberEntity;
import com.mouda.api.jpa.repository.MoimMemberJpaRepository;
import com.mouda.api.moim.domain.Member;
import com.mouda.api.moim.domain.Moim;
import com.mouda.api.moim.domain.MoimRepository;
import com.mouda.api.moim.domain.MoimRole;
import com.mouda.api.support.MemberFixture;
import com.mouda.api.support.MoimFixture;

@Transactional
@SpringBootTest
class MoimServiceTest {

	@Autowired
	MoimService moimService;

	@Autowired
	MoimRepository moimRepository;

	@Autowired
	MoimMemberJpaRepository moimMemberJpaRepository;

	@Autowired
	MemberFixture memberFixture;

	@Test
	void 모임을_생성한다() {
		// given
		Member member = memberFixture.create("testMember");
		Moim moim = MoimFixture.create("testMoim");

		// when
		moimService.createMoim(moim, member);

		// then
		int expected = moimRepository.findAll().getSize();
		assertThat(expected).isEqualTo(1);
	}

	@Test
	void 현재_시점보다_이른_모임을_생성할_수_없다() {
		// given
		Member member = memberFixture.create("testMember");
		Moim moim = MoimFixture.create("testMoim", LocalDate.now().minusDays(2));

		// when & then
		assertThatThrownBy(() -> moimService.createMoim(moim, member))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("Moim dateTime is before now");
	}

	@Test
	void 이미_존재하는_모임_이름으로_생성할_수_없다() {
		// given
		Member member = memberFixture.create("testMember");
		Moim existedMoim = MoimFixture.create("testMoim");
		moimService.createMoim(existedMoim, member);

		Moim newMoim = MoimFixture.create("testMoim");

		// when & then
		assertThatThrownBy(() -> moimService.createMoim(newMoim, member))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("Moim name already exist");
	}

	@Test
	void 모임을_만든_멤버는_모임의_호스트가_되며_자동으로_참여_처리가_된다() {
		// given
		Member member = memberFixture.create("testMember");
		Moim existedMoim = MoimFixture.create("testMoim");

		// when
		long createdMoimId = moimService.createMoim(existedMoim, member);

		// then
		List<MoimMemberEntity> moimMemberEntities = moimMemberJpaRepository.findByMoimId(createdMoimId);
		assertThat(moimMemberEntities).hasSize(1);
		assertThat(moimMemberEntities.get(0).getMemberId()).isEqualTo(member.getId());
		assertThat(moimMemberEntities.get(0).getMoimRole()).isEqualTo(MoimRole.HOST.name());
	}

	@Test
	void 모임에_참여한다() {
		// given
		Member member = memberFixture.create("testMember");
		Moim moim = MoimFixture.create("testMoim");
		long createdMoimId = moimService.createMoim(moim, member);

		// when
		Member newMember = memberFixture.create("newMember");
		moimService.enterMoim(createdMoimId, newMember);

		//then
		List<MoimMemberEntity> moimMemberEntities = moimMemberJpaRepository.findByMoimId(createdMoimId);
		assertThat(moimMemberEntities).hasSize(2);
	}

	@Test
	void 이미_참여한_모임에는_참여할_수_없다() {
		Member member = memberFixture.create("testMember");
		Moim moim = MoimFixture.create("testMoim");
		long createdMoimId = moimService.createMoim(moim, member);

		Member newMember = memberFixture.create("newMember");
		moimService.enterMoim(createdMoimId, newMember);

		// when & then
		assertThatThrownBy(() -> moimService.enterMoim(createdMoimId, newMember))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("member already entered (id : " + newMember.getId() + ")");
	}
}
