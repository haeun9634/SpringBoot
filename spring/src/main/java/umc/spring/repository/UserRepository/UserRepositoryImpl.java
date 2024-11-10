package umc.spring.repository.UserRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QReview;
import umc.spring.domain.QUser;
import umc.spring.domain.Review;
import umc.spring.domain.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QUser qUser = QUser.user;
    private final QReview qReview = QReview.review;

    //유저 정보 가져오는 쿼리
    @Override
    public List<User> findUserDataByUserId(Long userId) {
        User user = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.id.eq(userId))
                .fetchOne();

        return List.of(user);//사용자 정보 넘김
    }

    //사용자가 작성한 리뷰 가져오는 쿼리
    @Override
    public List<Review> findReviewByUserId(Long userId, Long lastReviewId, Long limit) {
        User user = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.id.eq(userId))
                .fetchOne();

        System.out.println(user);

        return jpaQueryFactory
                .select(qReview)
                .from(qReview)
                .where(qReview.user.eq(user))
                .where(qReview.id.gt(lastReviewId))  // lastReviewId 이후의 리뷰만 조회
                .orderBy(qReview.createdAt.desc()) // createAt 기준 내림차순, 최신순 정렬
                .limit(limit)
                .fetch();

    }
}
