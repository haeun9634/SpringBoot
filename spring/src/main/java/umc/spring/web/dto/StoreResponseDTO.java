package umc.spring.web.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO{
        List<MissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElments;
        Boolean isFirst;
        Boolean isLast;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDTO{
        String content;
        LocalDate deadline;
        Long point;
    }

}
