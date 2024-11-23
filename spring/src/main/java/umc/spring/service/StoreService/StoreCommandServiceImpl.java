package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayLoad.code.status.ErrorStatus;
import umc.spring.apiPayLoad.exception.GeneralException;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.StoreMap;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.RegionRepository.RegionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRegionRepository.StoreRegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.StoreResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRegionRepository storeRegionRepository; // Custom Repository 사용
    private final StoreRepository storeRepository;  // 기본 Store Repository
    private final RegionRepository regionRepository; // 기본 Region Repository
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public StoreMap addStoreToRegion(Long storeId, Long regionId) {

        Store store = findStoreById(storeId);

        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));


        boolean exists = storeRegionRepository.existsByStoreAndRegion(storeId, regionId);
        if (exists) {
            new GeneralException(ErrorStatus.DUPLICATE_STORE_REGION);;
        }


        StoreMap storeMap = storeRegionRepository.dynamicQueryInsertStoreRegion(storeId, regionId);

        return storeMap;
    }

    @Override
    public Review addStoreReview(Long storeId, Long userId, String content, Long score){
        Store store = findStoreById(storeId);
        User user = findUserById(userId);

        Review review = new Review(null, content, score, user, store);

        review = reviewRepository.save(review);

        return review;
    }

    @Override
    public StoreResponseDTO.StoreMissionResponseDTO addStoreMission(Long storeId, Long missionId) {
        Store store = findStoreById(storeId);
        Mission mission = findMissionById(missionId);

        // 미션에 가게 설정
        mission.setStore(store);

        // 미션 저장
        missionRepository.save(mission);

        return StoreConverter.toStoreMissionResultDTO(store, mission);

    }


    @Override
    public Long changeMissionStatus(Long userId, Long storeId, Long missionId) {
        Store store = findStoreById(storeId);
        Mission mission = findMissionById(missionId);
        User user = findUserById(userId);

//        userMissionRepository.findByUserIdMissionId(userId, missionId)
//                .ifPresent(m -> {
//                    throw new GeneralException(ErrorStatus.ALREADY_CHALLENGE_MISSION);
//                });

        // 미션이 해당 Store에 속하지 않는 경우 예외 처리
        if (!mission.getStore().equals(store)) {
            throw new GeneralException(ErrorStatus.NOT_STORE_MISSION);
        }


        UserMission newUserMission = new UserMission(user, mission);
        userMissionRepository.save(newUserMission);

        return newUserMission.getMission().getId();
    }

    // 데이터 조회 메서드
    private Store findStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
    }

    private Mission findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
    }

}
