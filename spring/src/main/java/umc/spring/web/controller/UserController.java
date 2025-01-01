package umc.spring.web.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.config.security.JwtTokenProvider;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.service.MailService;
import umc.spring.domain.User;

import java.util.HashMap;


@RestController
public class UserController {

    private MailService mailService;
    private JwtTokenProvider tokenProvider;
    private UserRepository userRepository;

    // 인증 이메일 전송
    @PostMapping("/mailSend")
    public ResponseEntity<?> mailSend(@RequestParam("mail") String mail) {
        HashMap<String, Object> response = new HashMap<>();
        System.out.println("ㅇㅇㅇ");

        mailService.sendMail(mail);
        response.put("success", true);
        response.put("message", "인증 메일이 전송되었습니다.");
        return ResponseEntity.ok(response);

    }

    @PostMapping("/mailSendForSignup")
    public ResponseEntity<?> mailSendForSignup(@RequestParam("mail") String mail) {
        HashMap<String, Object> response = new HashMap<>();

        try {

            mailService.sendMail(mail);
            response.put("success", true);
            response.put("message", "인증 메일이 전송되었습니다.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    //비밀번호 재설정을 위한 메일 전송
    @PostMapping("/mailSendForCheckEmail")
    public ResponseEntity<?> mailSendForCheckEmail(@RequestParam("mail") String mail) {
        HashMap<String, Object> response = new HashMap<>();

        try {

            mailService.sendMail(mail);
            response.put("success", true);
            response.put("message", "비밀번호 찾기 인증 메일이 전송되었습니다.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    //토큰 재발행
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Claims claims;
            try {
                claims = tokenProvider.validateAndGetClaims(token);
            } catch (ExpiredJwtException e) {
                claims = e.getClaims();
            }

            if (claims != null) {
                User user = userRepository.findById(Long.parseLong(claims.getSubject())).get();
                if (user != null) {
                    String newToken = tokenProvider.create(user);
                    return ResponseEntity.ok().body("새로운 토큰: " + newToken);
                }
            }

            return ResponseEntity.badRequest().body("유효하지 않은 사용자 정보입니다.");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("유효하지 않은 토큰입니다.");
        }
    }

}
