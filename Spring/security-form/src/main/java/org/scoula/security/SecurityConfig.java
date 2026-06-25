package org.scoula.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


/*
* Spring
* */
@Configuration
@EnableWebSecurity  // 필터체인 활성화
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 문자셋필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    /*
    * CSRF : 로그인한 사용자를 악의적인 사이트에서 몰래 요청을 보내게 하는 공격
    * */

    // 시큐리티 세팅할 공간
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // CSRF 필터 앞에다 encodingFilter를 놓겠다.
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        http.authorizeRequests() //  요청 권한 설정
                .antMatchers("/security/all")
                    .permitAll()
                .antMatchers("/security/admin")
                    .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member")
                    .access("hasRole('ROLE_MEMBER')");

        // form 기반 로그인 활성화
        http.formLogin()
                .loginPage("/security/login")  // 로그인 페이지 커스텀
                .loginProcessingUrl("/security/login") // 스프링 기본제공 POST 요청 시 로그인 시도
                .defaultSuccessUrl("/");

        http.logout()
                .logoutUrl("/security/logout")  // POST 요청을 보내면 로그아웃 시도
                .invalidateHttpSession(true)
                .deleteCookies("JSESSION-ID") // 삭제할 쿠키
                .logoutSuccessUrl("/security/logout");  // 로그아웃 성공하면 이동할 페이지
    }


    // 테스트용으로 메모리 상에 사용자 정보 등록 (임시 db)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // 관리자 계정
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$J9iXOVmvTiL26pkXgKnev.7UgRKly3Fidp9BjjLA5NZepZJ.NcJ4S")
                .roles("ADMIN", "MEMBER");

        // 일반 회원
        auth.inMemoryAuthentication()
                .withUser("member")
                .password("{noop}1234")
                .roles("MEMBER");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}