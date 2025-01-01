package umc.spring.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity //Spring Security 설정 활성화
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }


    @Bean //filterchain 정의, httpSecurity 객체를 통해 보안 설정 구성
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests//접근 제어
                        //requestMatchers //특정 url 패턴에 대한 접근 권한 설정
                        .requestMatchers("/", "/home", "/signup", "/users/signup","/css/**",
                                "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        //.permitAll()은 인증 없이 접근 가능한 경로
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        //.hasRole("ADMIN")은 'ADMIN" 역할을 가진 사용자만 접근 가능하도록 제한
                        .anyRequest().authenticated()
                        //그외 모든 요청에 대한 인증 요구

                )
                .formLogin((form) -> form//폼 기반 로그인
                        .loginPage("/login")//커스텀 로그인 페이지를 /login경로로 지정
                        .defaultSuccessUrl("/home", true)//로그인 성공시, /home으로 리다이렉트
                        .permitAll()//로그인 경로는 모든 사용자가 접근 가능하도록 설정
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")//로그아웃 경로
                        .logoutSuccessUrl("/login?logout")//로그아웃 성공시 여기로 리다이엑트
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)  // 커스텀 유저 서비스 주입
                .passwordEncoder(passwordEncoder());  // 비밀번호 인코더 주입

        return authenticationManagerBuilder.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {//비밀번호 암호화
        return new BCryptPasswordEncoder();
    }


}