package umc.spring.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QReview qReview = QReview.review;
    private final QUser qUser = QUser.user;
    private final QStore qStore = QStore.store;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Review dynamicQueryInsertReview(Long userId, String content, Float score, Long storeId) {
        User user = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.id.eq(userId))
                .fetchOne();

        Store store = jpaQueryFactory.selectFrom(qStore)
                .where(qStore.id.eq(storeId))
                .fetchOne();

        Review review = Review.builder()
                .content(content)
                .score(score)
                .user(user)
                .store(store)
                .build();

        entityManager.persist(review);

        return review;
    }
}
