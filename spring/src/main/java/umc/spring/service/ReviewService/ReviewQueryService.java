package umc.spring.service.ReviewService;

import umc.spring.domain.Review;

import java.util.List;

public interface ReviewQueryService {
    Review addReview(Long userId, String content, Long score, Long storeId);
}
