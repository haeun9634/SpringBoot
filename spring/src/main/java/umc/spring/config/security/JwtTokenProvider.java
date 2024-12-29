package umc.spring.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import umc.spring.domain.User;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class JwtTokenProvider {

    private static final String SECRET_KEY="NMA8JPctFuna59f5";

    public String create(User user) {
        Date expireDate=Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS));
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY) //JWT 서명 생성
                .setSubject(String.valueOf(user.getId())) //사용자 식별자 넣음
                .setIssuer("todo app")
                .setIssuedAt(new Date())//토큰 발급 시간
                .setExpiration(expireDate)//토큰 만료시간, 1일 후
                .compact();//문자열로 반환
    }

    //토큰 검증 및 사용자 ID 추출
    public String validateAndGetUserId(String token) {
        Claims claims=Jwts.parser()
                .setSigningKey(SECRET_KEY) //서명 검증에 사용할 키
                .parseClaimsJws(token) //실제 서명 검증,
                .getBody();
        return claims.getSubject(); //사용자 id 반환
    }

    //토큰 검증
    public Claims validateAndGetClaims(String token) {
        try {
            // 토큰이 유효할 경우 Claims 반환
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token) //서명 검증
                    .getBody();
        } catch (ExpiredJwtException e) {
            // 토큰이 만료된 경우에도 Claims 반환 가능
            return e.getClaims();
        }
    }

}
