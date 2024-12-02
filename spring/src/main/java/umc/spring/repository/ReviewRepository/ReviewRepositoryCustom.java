package umc.spring.repository.ReviewRepository;


import umc.spring.domain.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    Review dynamicQueryInsertReview(Long userId, String content, Float score, Long storeId);
}