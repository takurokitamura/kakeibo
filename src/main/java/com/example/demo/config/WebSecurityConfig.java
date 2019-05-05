package com.example.demo.config;

import com.example.demo.service.JpaUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Webセキュリティコンフィグ。
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        // 認証状態によらず許可するパス
        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/bootstrap/css/**", "/bootstrap/js/**", "/jquery/**", "/images/**", "/fonts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 認証状態によらず許可するURL
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/account/register/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login") // ログインページのパス
                .loginProcessingUrl("/login") // 認証処理を起動させるパス
                .failureUrl("/login/?error") // ログイン処理失敗時の遷移先
                .successForwardUrl("/top") // ログイン成功時の繊維先
                .usernameParameter("accountid")// ユーザid
                .passwordParameter("password").permitAll(); // パスワード

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウト処理を起動させるパス
                .deleteCookies("JSESSIONID") // JsessionIdを削除
                .logoutSuccessUrl("/") // ログアウト完了時のパス
                .invalidateHttpSession(true).permitAll();
    }

    @Configuration
    protected static class AuthenticationConfiguration
            extends GlobalAuthenticationConfigurerAdapter {

        final JpaUserDetailsServiceImpl userDetailsService;

        @Autowired
        public AuthenticationConfiguration(JpaUserDetailsServiceImpl userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 認証するユーザーを設定する
            auth.userDetailsService(userDetailsService)
                    // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
                    .passwordEncoder(new BCryptPasswordEncoder());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}