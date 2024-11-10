package umc.spring.repository.MissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.QUser;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QUserMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission qMission = QMission.mission;
    private final QUserMission userMission = QUserMission.userMission;
    private final QUser qUser = QUser.user;

    @Override
    @Transactional
    public List<Mission> findInProgressMissions(Long userId, Long lastMissionId, int limit) {

        // 서브쿼리: 마지막으로 조회한 미션의 마감일 가져오기
        LocalDate lastDeadline = jpaQueryFactory
                .select(qMission.deadline)
                .from(qMission)
                .where(qMission.id.eq(lastMissionId))
                .fetchOne();


        // 메인 쿼리: 진행 중인 미션 조회
        return jpaQueryFactory
                .selectFrom(qMission)
                .innerJoin(userMission).on(qMission.id.eq(userMission.mission.id))
                .where(userMission.user.id.eq(userId))
                .where(userMission.status.eq(MissionStatus.CHALLENGING))
                .where(qMission.deadline.goe(lastDeadline))
                .orderBy(qMission.deadline.asc())
                .limit(limit)
                .fetch();
    }

    @Override
    @Transactional
    public List<Mission> findInCompleteMissions(Long userId, Long lastMissionId, int limit) {
        // 서브쿼리: 마지막으로 조회한 미션의 마감일 가져오기
        LocalDateTime lastModified = jpaQueryFactory
                .select(userMission.updatedAt)
                .from(userMission)
                .where(userMission.mission.id.eq(lastMissionId))
                .fetchOne();


        // 메인 쿼리: 진행 완료 상태인 미션 조회
        return jpaQueryFactory
                .selectFrom(qMission)
                .innerJoin(userMission).on(qMission.id.eq(userMission.mission.id))
                .where(userMission.user.id.eq(userId))
                .where(userMission.status.eq(MissionStatus.COMPLETE))
                .where(userMission.updatedAt.goe(lastModified))  // 수정일이 같거나 더 최근인 미션만 조회
                .orderBy(userMission.updatedAt.desc())  // 수정일을 내림차순으로 정렬
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Mission> findMissionsByMapId(Long userId, Long mapId, int offset, int limit) {

        //서브 쿼리: 유저 정보 가져오기
        User user = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.id.eq(userId))
                .fetchOne();

        // 쿼리: 현재 지역에서 아직 도전하지 않은 미션 목록
        return jpaQueryFactory.selectFrom(qMission)
                .leftJoin(userMission).on(userMission.mission.eq(qMission))
                .where(qMission.region.id.eq(mapId))
                .where(userMission.user.eq(user).or(userMission.user.isNull()))  // user인데 도전안한 상태 또는 user가 없는 상태
                .where(userMission.status.isNull())  // 도전하지 않은 상태
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Long countCompleteMissions(Long userId) {

        // 쿼리: 해당 유저가 완료한 미션 개수 카운트
        return jpaQueryFactory.select(userMission.count())
                .from(userMission)
                .innerJoin(qMission).on(qMission.id.eq(userMission.mission.id))  // 미션과 조인
                .where(userMission.user.id.eq(userId))  // 특정 유저에 대한 미션
                .where(userMission.status.eq(MissionStatus.COMPLETE))  // 완료된 미션
                .fetchOne();
    }
}
