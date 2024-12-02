package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMap;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDTO.StoreRegionResponseDTO toStoreRegionResultDTO(StoreMap storeMap){
        return StoreResponseDTO.StoreRegionResponseDTO.builder()
                .storeId(storeMap.getStore().getId())
                .storeName(storeMap.getStore().getName())
                .regionId(storeMap.getRegion().getId())
                .regionName(storeMap.getRegion().getMap_address())
                .build();


    }

    public static StoreResponseDTO.StoreMissionResponseDTO toStoreMissionResultDTO(Store store, Mission mission) {

        return StoreResponseDTO.StoreMissionResponseDTO.builder()
                .storeId(store.getId())
                .missionId(mission.getId())
                .missionName(mission.getName())
                .build();
    }


    public static StoreResponseDTO.MissionListDTO missionPreViewListDTO(Page<Mission> missionList){
        List<StoreResponseDTO.MissionDTO> missionPreViewDTOList = missionList.stream()
                .map(StoreConverter::missionPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElments(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
    public static StoreResponseDTO.MissionDTO missionPreViewDTO(Mission mission){
        return StoreResponseDTO.MissionDTO.builder()
                .content(mission.getContent())
                .deadline(mission.getDeadline())
                .point(Long.valueOf(mission.getPoint()))
                .build();
    }
}
