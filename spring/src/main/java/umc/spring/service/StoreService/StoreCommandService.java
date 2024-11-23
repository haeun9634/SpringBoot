package umc.spring.service.StoreService;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.StoreMap;
import umc.spring.web.dto.StoreResponseDTO;

public interface StoreCommandService {
    StoreMap addStoreToRegion(Long storeId, Long regionId);
    Review addStoreReview(Long storeId, Long userId, String content, Long score);
    StoreResponseDTO.StoreMissionResponseDTO addStoreMission(Long storeId, Long missionId);
    Long changeMissionStatus(Long userId, Long storeId, Long missionId);
}

