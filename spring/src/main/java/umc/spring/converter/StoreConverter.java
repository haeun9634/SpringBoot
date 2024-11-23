package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMap;
import umc.spring.web.dto.StoreResponseDTO;

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
}
