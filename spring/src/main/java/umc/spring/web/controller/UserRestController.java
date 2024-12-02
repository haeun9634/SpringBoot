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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayLoad.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.service.UserService.UserCommandServiceImpl;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ValidStoreExists;
import umc.spring.validation.annotation.ValidUserExists;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/{userId}/reviews")
    @Operation(summary = "사용자의 리뷰 목록 조회 API",description = "특정 사용자의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 아이디, path variable")
    })
    public ApiResponse<UserResponseDTO.ReviewListDTO> getReviewList(@ValidUserExists @PathVariable(name = "userId") Long userId,
                                                                    @CheckPage @RequestParam(name = "page") Integer page){
        Integer adjustedPage = page - 1;
        Page<Review> reviewList = userQueryService.getReviewList(userId,adjustedPage);
        return ApiResponse.onSuccess(UserConverter.reviewPreViewListDTO(reviewList));
    }

    @GetMapping("/{userId}/missions")
    @Operation(summary = "사용자의 미션 목록 조회 API",description = "특정 사용자의 미션의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호와 조회할 미션 상태를 넘겨주세요. 미션 상태는 CHALLENGING, COMPLETE가 있습니다. ")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 아이디, path variable")
    })
    public ApiResponse<UserResponseDTO.MissionListDTO> getMissionList(@ValidUserExists @PathVariable(name = "userId") Long userId,
                                                                      @CheckPage @RequestParam(name = "page") Integer page,
                                                                      @RequestParam(name = "status")MissionStatus missionStatus){
        Integer adjustedPage = page - 1;
        Page<Mission> missionList = userQueryService.getMissionList(userId, adjustedPage, missionStatus);
        return ApiResponse.onSuccess(UserConverter.missionPreViewListDTO(missionList));
    }
}
