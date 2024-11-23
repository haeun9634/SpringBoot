package umc.spring.repository.UserMissionRepository;

import umc.spring.domain.mapping.UserMission;
import java.util.List;
import java.util.Optional;

public interface UserMissionRepositoryCustom {
    Optional<UserMission> findByUserIdMissionId(Long userId, Long missionId);
}
