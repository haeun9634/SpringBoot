package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.repository.UserRepository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUserDataByUserId(Long userId) {
        return userRepository.findUserDataByUserId(userId);
    }

    @Override
    public List<Review> getUserReviewByUserId(Long userId, Long lastReviewId, Long limit) {
        return userRepository.findReviewByUserId(userId, lastReviewId, limit);
    }
}
