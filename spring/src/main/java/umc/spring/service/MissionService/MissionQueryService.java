package umc.spring.service.MissionService;

import umc.spring.domain.Mission;

import java.util.List;

public interface MissionQueryService {
   List<Mission> getInProgressMissionsForUser(Long userId, Long lastMissionId, int limit);
   List<Mission> getInCompleteMissionsForUser(Long userId, Long lastMissionId, int limit);
   List<Mission> getMissionsByMapId(Long userId, Long mapId, int offset, int limit);
   Long countMissionByUserId(Long userId);
}
