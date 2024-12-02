package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayLoad.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.StoreMap;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ValidStoreExists;
import umc.spring.validation.annotation.ValidUserExists;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.converter.StoreConverter;
import umc.spring.web.dto.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Validated
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

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


    @GetMapping("/{storeId}/missions")
    @Operation(summary = "가게의 미션 목록 조회 API",description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 아이디, path variable")
    })
    @Validated
    public ApiResponse<StoreResponseDTO.MissionListDTO> getMissionList(@ValidStoreExists @PathVariable(name = "storeId") Long storeId,
                                                                       @CheckPage @RequestParam(name = "page") Integer page){
        Integer adjustedPage = page - 1;
        Page<Mission> missionList = storeQueryService.getMissionList(storeId,adjustedPage);
        return ApiResponse.onSuccess(StoreConverter.missionPreViewListDTO(missionList));

    }

}
