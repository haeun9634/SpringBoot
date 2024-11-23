package umc.spring.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.spring.validation.annotation.ValidMissionChallenge;
import umc.spring.validation.annotation.ValidMissionExists;
import umc.spring.validation.annotation.ValidStoreExists;
import umc.spring.validation.annotation.ValidUserExists;

public class StoreRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreRegionRequestDTO {
        @ValidStoreExists
        private Long storeId;
        private Long regionId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreReviewRequestDTO{
        @ValidStoreExists
        private Long storeId;
        private String content;
        private Long score;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionRequestDTO {
        @NotNull
        private Long storeId;

        @NotNull
        @Valid
        @ValidMissionChallenge
        private UserMissionDTO userMission;  // missionId와 userId를 포함하는 객체로 변경

        @Getter
        public static class UserMissionDTO {
            @ValidMissionExists
            private Long missionId;
            @ValidUserExists
            private Long userId;
        }
    }


}
