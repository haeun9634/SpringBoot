package umc.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.enums.AgreeStatus;
import umc.spring.domain.enums.AlertType;
import umc.spring.service.AlertService.AlertQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.UserService.UserQueryService;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			// 각 서비스 Bean 호출 및 테스트
//			testUserQueryService(context);
//			testReviewQueryService(context);
//			testMissionQueryService(context);
//			testAlertQueryService(context);
		};
	}

	private void testUserQueryService(ApplicationContext context) {
		UserQueryService userQueryService = context.getBean(UserQueryService.class);
		Long userId = 1L;
		Long lastReviewId = 0L;
		Long limit = 10L;

		System.out.println("사용자 관련 테스트.");
		userQueryService.getUserDataByUserId(userId).forEach(System.out::println);
		userQueryService.getUserReviewByUserId(userId, lastReviewId, limit).forEach(System.out::println);
	}

	private void testReviewQueryService(ApplicationContext context) {
		ReviewQueryService reviewQueryService = context.getBean(ReviewQueryService.class);
		Long userId = 1L;
		String content = "Great store!";
		Float score = 5.0F;
		Long storeId = 1L;

		System.out.println("리뷰 생성 테스트");
		Review review = reviewQueryService.addReview(userId, content, score, storeId);
		System.out.println("Added review: " + review);
	}

	private void testMissionQueryService(ApplicationContext context) {
		MissionQueryService missionQueryService = context.getBean(MissionQueryService.class);
		Long userId = 1L;
		Long lastMissionId = 1L;
		int limit = 10;

		System.out.println("미션 관련 테스트...");
		List<Mission> inProgressMissions = missionQueryService.getInProgressMissionsForUser(userId, lastMissionId, limit);
		List<Mission> inCompleteMissions = missionQueryService.getInCompleteMissionsForUser(userId, 2L, limit);

		System.out.println("In-Progress Missions:");
		for (Mission mission : inProgressMissions) {
			System.out.println(mission);
		}

		System.out.println("Incomplete Missions:");
		for (Mission mission : inCompleteMissions) {
			System.out.println(mission);
		}

		System.out.println(missionQueryService.getMissionsByMapId(userId, 1L,0,limit));
		System.out.println(missionQueryService.countMissionByUserId(userId));
	}

	private void testAlertQueryService(ApplicationContext context) {
		AlertQueryService alertQueryService = context.getBean(AlertQueryService.class);
		Long userId = 1L;
		AlertType alertType = AlertType.NEW_MISSION;
		AgreeStatus agreeStatus = AgreeStatus.DISAGREE;

		System.out.println("알림 관련 테스트");
		alertQueryService.updateAlertAgreement(userId, alertType, agreeStatus);
	}
}

