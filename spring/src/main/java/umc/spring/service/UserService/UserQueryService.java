package umc.spring.service.UserService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface UserQueryService {
    List<User> getUserDataByUserId(Long userId);
    List<Review> getUserReviewByUserId(Long userId, Long lastReviewId, Long limit);
    Page<Review> getReviewList(Long UserId, Integer page);
    Page<Mission> getMissionList(Long UserId, Integer page, MissionStatus missionStatus);
}
