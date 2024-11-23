package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayLoad.ApiResponse;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.StoreMap;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ValidStoreExists;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.converter.StoreConverter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/addRegion")
    public ApiResponse<StoreResponseDTO.StoreRegionResponseDTO> addStoreToRegion(@RequestBody @Valid StoreRequestDTO.StoreRegionRequestDTO request) {
        StoreMap storeMap = storeCommandService.addStoreToRegion(request.getStoreId(), request.getRegionId());

        return ApiResponse.onSuccess(StoreConverter.toStoreRegionResultDTO(storeMap));
    }

    @PostMapping("/addReview")
    public ApiResponse<StoreResponseDTO.StoreReviewResponseDTO> addStoreReview(
            @RequestBody @Valid StoreRequestDTO.StoreReviewRequestDTO request){
        Review review = storeCommandService.addStoreReview(request.getStoreId(),1L, request.getContent(), request.getScore());
        //원래는 토큰을 통해 userId를 얻어와야 하지만 현재 그부분까지 구현되지 않았으므로 직접 값을 넣어주었다.

        StoreResponseDTO.StoreReviewResponseDTO responseDTO = new StoreResponseDTO.StoreReviewResponseDTO(review.getId());
        return ApiResponse.onSuccess(responseDTO);
    }


    @PostMapping("/addMission")
    public ApiResponse<StoreResponseDTO.StoreMissionResponseDTO> addStoreMission(@RequestBody @Valid StoreRequestDTO.StoreMissionRequestDTO request){
        StoreResponseDTO.StoreMissionResponseDTO storeMissionResponseDTO = storeCommandService.addStoreMission(request.getStoreId(), request.getUserMission().getMissionId());

        return ApiResponse.onSuccess( (storeMissionResponseDTO));
    }

    @PatchMapping("/missionChallenge")
    public ApiResponse<String> addStoreReview(@RequestBody @Valid StoreRequestDTO.StoreMissionRequestDTO request) {
        Long missionId = storeCommandService.changeMissionStatus(
                request.getUserMission().getUserId(),
                request.getStoreId(),
                request.getUserMission().getMissionId()
        );

        return ApiResponse.onSuccess("미션 " + missionId + " 도전 성공되었습니다.");
    }

}
