package umc.spring.config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.boot.autoconfigure.mail.MailProperties;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailConfig {

    private final MailProperties mailProperties;

    public MailConfig(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        mailSender.getJavaMailProperties().put("mail.smtp.auth", true);
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", true);
        return mailSender;
    }
}
