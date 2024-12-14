package com.example.dajewelry_cuoiky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // Cho phép truy cập các trang này mà không cần đăng nhập
                .requestMatchers(
                        "/register/**",
                        "/home/**",
                        "/",
                        "/api/products",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/php/**",
                        "/webfonts/**"
                ).permitAll()
                // Các trang yêu cầu người dùng phải đăng nhập
                .requestMatchers("/admin/**").authenticated() // Đảm bảo rằng người dùng đã đăng nhập
                .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ những người có role "ADMIN" mới được vào trang admin
                .requestMatchers("/users").hasRole("ADMIN") // Chỉ ADMIN mới truy cập
                .and()
                // Cấu hình trang đăng nhập
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                // Cấu hình đăng xuất
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true) // Xóa session khi đăng xuất
                .deleteCookies("JSESSIONID") // Xóa cookie
                .permitAll();

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
