package com.example.demo.config.security.oauth2;

import com.example.demo.controller.advice.SpringSecurityAccessDeniedHandler;
import com.example.demo.controller.advice.SpringSecurityAuthenticationEntryPoint;
import com.example.demo.core.helper.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("${security.oauth.unauth.paths}")
    private String[] AUTH_WHITE_LIST;
    private static final String RESOURCE_ID = "resource_id";

    @Autowired
    private SpringSecurityAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private SpringSecurityAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false).accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint).tokenExtractor(new CustomTokenExtractor());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(AUTH_WHITE_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and();
    }

}

class CustomTokenExtractor implements TokenExtractor {
    @Override
    public Authentication extract(HttpServletRequest request) {
        Authentication authentication = new PreAuthenticatedAuthenticationToken(RequestHelper.getAccessToken(request), "");
        if (authentication.isAuthenticated()) {
            request.setAttribute("user", authentication.getDetails());
        }
        return authentication;
    }
}
