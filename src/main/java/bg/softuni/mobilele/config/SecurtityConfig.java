package bg.softuni.mobilele.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import bg.softuni.mobilele.service.MobileleUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurtityConfig {

        private final MobileleUserDetailService userDetailsService;

        SecurtityConfig(MobileleUserDetailService userDetailsService) {
                this.userDetailsService = userDetailsService;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

                return httpSecurity
                                .authorizeHttpRequests(
                                                authorizeRequest -> authorizeRequest
                                                                .requestMatchers(PathRequest.toStaticResources()
                                                                                .atCommonLocations())
                                                                .permitAll()
                                                                .requestMatchers("/", "/test", "/users/login",
                                                                                "/users/register", "/users/login-error")
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.GET, "/offers/all",
                                                                                "/brands/all")
                                                                .permitAll()
                                                                .anyRequest().authenticated())
                                .formLogin(
                                                formLogin -> formLogin
                                                                .loginPage("/users/login")
                                                                .usernameParameter("username")
                                                                .passwordParameter("password")
                                                                .defaultSuccessUrl("/")
                                                                .failureForwardUrl("/users/login-error"))
                                .logout(
                                                logout -> logout
                                                                .logoutUrl("/users/logout")
                                                                .logoutSuccessUrl("/")
                                                                .deleteCookies("JSESSIONID")
                                                                .invalidateHttpSession(true))
                                .userDetailsService(userDetailsService)
                                .build();

        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        }
}
