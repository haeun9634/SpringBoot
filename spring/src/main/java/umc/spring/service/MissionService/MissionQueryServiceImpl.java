package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public List<Mission> getInProgressMissionsForUser(Long userId, Long lastMissionId, int limit) {
        return missionRepository.findInProgressMissions(userId, lastMissionId, limit);
    }

    @Override
    public List<Mission> getInCompleteMissionsForUser(Long userId, Long lastMissionId, int limit) {
        return missionRepository.findInCompleteMissions(userId, lastMissionId, limit);
    }

    @Override
    public List<Mission> getMissionsByMapId(Long userId, Long mapId, int offset, int limit) {
        return missionRepository.findMissionsByMapId(userId, mapId, offset, limit);
    }

    @Override
    public Long countMissionByUserId(Long userId){
        return missionRepository.countCompleteMissions(userId);
    }
}
