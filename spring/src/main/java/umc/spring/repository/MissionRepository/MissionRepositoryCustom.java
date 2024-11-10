package umc.spring.repository.MissionRepository;

import umc.spring.domain.Mission;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findInProgressMissions(Long userId, Long lastMissionId, int limit);
    List<Mission> findInCompleteMissions(Long userId, Long lastMissionId, int limit);
    List<Mission> findMissionsByMapId(Long userId, Long mapId, int offset, int limit);
    Long countCompleteMissions(Long userId);
}

