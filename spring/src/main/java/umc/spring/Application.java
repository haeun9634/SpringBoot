package umc.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
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


}

@Component
class MailSenderChecker {

	@Autowired
	private JavaMailSender mailSender;

	@EventListener(ApplicationReadyEvent.class)
	public void checkMailSender() {
		if (mailSender != null) {
			System.out.println("JavaMailSender Bean is available.");
		} else {
			System.out.println("JavaMailSender Bean is NOT available.");
		}
	}
}
