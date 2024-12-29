package umc.spring.repository.UserRepository;

import umc.spring.domain.Review;
import umc.spring.domain.User;
import java.util.List;

public interface UserRepositoryCustom {
     User findUserDataByUserId(Long userId);
     List <Review> findReviewByUserId(Long userId, Long lastReviewId, Long limit);
}
