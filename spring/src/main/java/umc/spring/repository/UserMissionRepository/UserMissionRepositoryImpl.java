package umc.spring.repository.UserMissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.QUser;
import umc.spring.domain.User;
import umc.spring.domain.mapping.QUserMission;
import umc.spring.domain.mapping.UserMission;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserMissionRepositoryImpl implements UserMissionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QUserMission qUserMission = QUserMission.userMission;

    @Override
    public Optional<UserMission> findByUserIdMissionId(Long userId, Long missionId){

        UserMission userMission = jpaQueryFactory.selectFrom(qUserMission)
                .where(qUserMission.user.id.eq(userId))
                .where(qUserMission.mission.id.eq(missionId))
                .fetchOne(); // 단일 객체 반환
        return Optional.ofNullable(userMission); // Optional로 래핑
    }
}
