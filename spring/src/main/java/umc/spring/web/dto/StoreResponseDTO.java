package umc.spring.web.dto;

import lombok.*;

public class StoreResponseDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreRegionResponseDTO {
        private Long storeId;
        private Long regionId;
        private String storeName;
        private String regionName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreReviewResponseDTO{
        private Long reviewId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreMissionResponseDTO {
        private Long storeId;
        private Long missionId;
        private String storeName;
        private String missionName;
    }



}
