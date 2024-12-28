package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayLoad.exception.handler.PreferenceFoodHandler;
import umc.spring.converter.UserConverter;
import umc.spring.domain.PreferenceFood;
import umc.spring.domain.User;
import umc.spring.domain.enums.FoodCategory;
import umc.spring.domain.mapping.UserPrefer;
import umc.spring.repository.PreferenceFoodRepository.PreferenceFoodRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.apiPayLoad.code.status.ErrorStatus;

import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PreferenceFoodRepository preferenceFoodRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDto request){

        User newUser = UserConverter.toUser(request);

        newUser.encodePassword(passwordEncoder.encode(request.getPassword()));

        List<UserPrefer> userPreferList = request.getPreferCategory().stream()
                .map(foodCategory -> {
                    PreferenceFood preferenceFood = preferenceFoodRepository
                            .findByFoodCategory(foodCategory)
                            .orElseThrow(() -> new PreferenceFoodHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));

                    return UserPrefer.builder()
                            .user(newUser)
                            .preferenceFood(preferenceFood)
                            .build();
                }).collect(Collectors.toList());

        // 로깅 추가
        System.out.println("User: " + newUser);
        System.out.println("User Prefers: " + userPreferList);

        // 3. User에 UserPrefer 설정
        newUser.addAllUserPrefers(userPreferList);

        // 4. User 저장
        return userRepository.save(newUser);
    }


}
