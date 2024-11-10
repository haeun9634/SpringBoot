package umc.spring.service.UserService;

import umc.spring.domain.Review;
import umc.spring.domain.User;
import java.util.List;

public interface UserQueryService {
    List<User> getUserDataByUserId(Long userId);
    List<Review> getUserReviewByUserId(Long userId, Long lastReviewId, Long limit);
}
