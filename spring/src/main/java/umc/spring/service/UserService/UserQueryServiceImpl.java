package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.UserRepository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public List<User> getUserDataByUserId(Long userId) {
        return userRepository.findUserDataByUserId(userId);
    }

    @Override
    public List<Review> getUserReviewByUserId(Long userId, Long lastReviewId, Long limit) {
        return userRepository.findReviewByUserId(userId, lastReviewId, limit);
    }

    @Override
    public Page<Review> getReviewList(Long UserId, Integer page) {
        User user = userRepository.findById(UserId).get();
        Page <Review> UserPage = reviewRepository.findAllByUser(user, PageRequest.of(page,10));
        return UserPage;
    }

    @Override
    public Page<Mission> getMissionList(Long UserId, Integer page, MissionStatus missionStatus) {
        User user = userRepository.findById(UserId).get();
        Page <Mission> MissionPage = missionRepository.findChallengingMissionsByUser(user, missionStatus, PageRequest.of(page,10));
        return MissionPage;
    }
}
