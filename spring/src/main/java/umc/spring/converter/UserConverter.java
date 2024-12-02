package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;
import umc.spring.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {


    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request){

        return User.builder()
                .nickname(request.getNickname())
                .address(request.getAddress())
                .phone(request.getPhone())
                .specAddress(request.getSpecAddress())
                .gender(request.getGender())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .UserPreferList(new ArrayList<>())
                .build();

    }

    public static UserResponseDTO.ReviewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<UserResponseDTO.ReviewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(UserConverter::reviewPreViewDTO).collect(Collectors.toList());

        return UserResponseDTO.ReviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElments(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
    public static UserResponseDTO.ReviewDTO reviewPreViewDTO(Review review){
        return UserResponseDTO.ReviewDTO.builder()
                .nickname(review.getUser().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }

    public static UserResponseDTO.MissionListDTO missionPreViewListDTO(Page<Mission> missionList){
        List<UserResponseDTO.MissionDTO> missionPreViewDTOList = missionList.stream()
                .map(UserConverter::missionPreViewDTO).collect(Collectors.toList());

        return UserResponseDTO.MissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElments(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
    public static UserResponseDTO.MissionDTO missionPreViewDTO(Mission mission){
        return UserResponseDTO.MissionDTO.builder()
                .content(mission.getContent())
                .deadline(mission.getDeadline())
                .point(Long.valueOf(mission.getPoint()))
                .build();
    }

}
