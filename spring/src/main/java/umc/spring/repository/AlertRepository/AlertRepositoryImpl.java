package umc.spring.repository.AlertRepository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.QAlert;
import umc.spring.domain.QUser;
import umc.spring.domain.enums.AgreeStatus;
import umc.spring.domain.enums.AlertType;
import umc.spring.domain.mapping.QUserAlert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class AlertRepositoryImpl implements AlertRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QUser qUser = QUser.user;
    private final QAlert qAlert = QAlert.alert;
    private final QUserAlert qUserAlert = QUserAlert.userAlert;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateUserAlertAgreement(Long userId, AlertType alertType, AgreeStatus agreeStatus) {


        // 사용자의 알림 타입에 대해 동의 상태를 업데이트
        jpaQueryFactory
                .update(qUserAlert)  // UserAlert 엔티티 업데이트
                .set(qUserAlert.alertAgree, agreeStatus)  // 동의 상태 업데이트
                .where(qUserAlert.user.id.eq(userId))  // 사용자 ID 조건
                .where(qUserAlert.alert.id.in(
                        JPAExpressions
                                .select(qAlert.id)  // Alert 엔티티의 ID를 서브쿼리로 선택
                                .from(qAlert)
                                .where(qAlert.alertType.eq(alertType))  // 알림 타입 조건
                ))
                .execute();
    }
}
