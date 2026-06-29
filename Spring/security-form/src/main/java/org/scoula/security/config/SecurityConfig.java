package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

/*
* Spring Security의 보안 설정 클래스
* */
@Configuration
@EnableWebSecurity // 필터체인 활성화
@RequiredArgsConstructor
@Log4j2
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    // 문자셋필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    /*
    * CSRF : 로그인한 사용자를 악의적인 사이트에서 몰래 요청을 보내게하는 공격
    * */
    // 시큐리티 세팅할 공간
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF 필터 앞에다 encodingFilter를 놓겠다.
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        // CORS 설정 추가
        http.cors();

        // URL별 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security/all")
                    .permitAll()// 모든 권한 접근 허용
                .antMatchers("/security/admin")
                    .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member")
                    .access("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')");

        // form 기반 로그인 활성화
        http.formLogin()
                .loginPage("/security/login") // 로그인 페이지 커스텀
                .loginProcessingUrl("/security/login") // 스프링 기본제공 POST요청시 로그인시도
                .defaultSuccessUrl("/");

        http.logout()
                .logoutUrl("/security/logout") // POST 요청을보내면 로그아웃 시도
                .invalidateHttpSession(true) // 세션 초기화
                .deleteCookies("JSESSION-ID") // 삭제할 쿠키
                .logoutSuccessUrl("/security/logout"); // 로그아웃성공하면 이동할 페이지

        http.sessionManagement()
                .maximumSessions(1) // 동시 세션 수 제한
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/security/login?expired");


    }


    // 테스트용으로 메모리 상에 사용자정보 등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService) // userDetailService
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration(); // CORS 설정 객체

        // origin -> 출처
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 출처에 대해 허용

        // 허용할 HTTP 메소드 목록
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // 모든 요청 헤더 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // 쿠키, Authorization 헤더 등 자격증명을 포함한 요청 허용
        configuration.setAllowCredentials(true);

        // 특정 URL 경로 패턴에 대해 CORS 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}

