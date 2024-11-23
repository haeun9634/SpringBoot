package umc.spring.converter;

import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;
import umc.spring.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

}
