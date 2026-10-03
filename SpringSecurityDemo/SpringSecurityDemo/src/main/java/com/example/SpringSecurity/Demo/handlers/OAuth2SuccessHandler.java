package com.example.SpringSecurity.Demo.handlers;

import com.example.SpringSecurity.Demo.entities.User;
import com.example.SpringSecurity.Demo.service.JwtService;
import com.example.SpringSecurity.Demo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Value("${deploy.env}")
    private String deployEnv;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException{
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) token.getPrincipal();

        String email = oauth2User.getAttribute("email");

        User user = userService.getUsrByEmail(email);

        if(user == null){
            User newUser = User.builder()
                    .name(oauth2User.getAttribute("name"))
                    .email(email)
                    .build();
            user = userService.save(newUser);
        }

        String AccessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true); //make sure that cookie cannot be accessed by any other means other than HTTP Methods anc cannot be accessed by javascript
        cookie.setSecure("production".equals(deployEnv));  // Use in HTTPS scenario.
        response.addCookie(cookie);

        String frontendUrl = "http://localhost:8080/home.html?token="+AccessToken;

        getRedirectStrategy().sendRedirect(request, response, frontendUrl);
     }
}
