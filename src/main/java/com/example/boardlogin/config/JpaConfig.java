package com.example.boardlogin.config;

import com.example.boardlogin.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "accountAuditAware")
public class JpaConfig {

    @Autowired
    HttpServletRequest request;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {

                String name = String.valueOf(request.getRequestURL());
                System.out.println(name);

                return Optional.empty();
            }
        };
    }

}
