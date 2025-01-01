package umc.spring.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "haeun9634@gmail.com";

    // 각 사용자의 인증 번호를 저장하는 맵
    private final Map<String, Integer> emailVerificationMap = new HashMap<>();


    public void sendMail(String mail) {
        int number = createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();
        System.out.println("number : "+number);

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");

            System.out.println("우왕");
            javaMailSender.send(message);
            System.out.println("야호");

            //사용자 이메일과 인증 번호 매핑 저장
            emailVerificationMap.put(mail, number);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public int getVerificationNumber(String mail) {
        return emailVerificationMap.getOrDefault(mail, -1); // 해당 이메일의 인증 번호 반환, 없으면 -1 반환
    }

    private int createNumber() {
        return (int)(Math.random() * (90000)) + 100000; //(int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public boolean checkVerificationNumber(String mail, int userNumber) {
        int storedNumber = getVerificationNumber(mail);
        return storedNumber == userNumber;
    }
}
