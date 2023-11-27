package com.example.boardlogin.config;

import com.example.boardlogin.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginUserAuditorAware implements AuditorAware<String> {

    private final HttpSession session;

    @Override
    public Optional<String> getCurrentAuditor() {

        UserVO user = (UserVO) session.getAttribute("name");

        if (user != null) {
            return Optional.ofNullable(user.getName());
        }

        return Optional.empty();
    }

}
