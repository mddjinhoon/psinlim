package org.mddjinhoon.demo.base1.config.auth;

import lombok.RequiredArgsConstructor;
import org.mddjinhoon.demo.base1.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
            .and()
                .logout()
                .logoutSuccessUrl("/")
            .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
/*
* authorizeRequests(): url별 권환관리를 설정하는 옵션의 시작점, antMatchers()을 사용하기 위함
* permitAll(): 전체 허가
* hasRole(): 인자에 값을 가진 사람만 허가
* anyRequest(): 나머지 url
* authenticated(): 인증된 사람 -> 로그인 한 사람?
* oauth2Login(): oauth2 로그인 기능 설정 진입점
* userInfoEndpoint(): 사용자 정보를 가져올 때의 설정
* userService(): 소설 서비스에서 가져온 데이터를 가공하기 위한 서비스 등록
* */