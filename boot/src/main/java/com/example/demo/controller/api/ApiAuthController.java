package com.example.demo.controller.api;

import com.example.demo.core.iservice.ApiController;
import com.example.demo.core.service.UserService;
import com.example.demo.entity.PrincipalUser;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@RestController
@ResponseBody()
@RequestMapping("/oauth")
public class ApiAuthController extends ApiController {
    @Autowired
    UserService userService;
    @Autowired
    TokenEndpoint tokenEndpoint;

    @RequestMapping(value = "/token", method = {RequestMethod.POST, RequestMethod.GET})
    public Map getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ResponseEntity responseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        return json(true, responseEntity.getBody(), "Success");
    }

    @RequestMapping(value = "/user/create", method = {RequestMethod.POST, RequestMethod.GET})
    public Map postView(User user) {
        if (user.getUsername() != null && user.getUsername().length() > 2) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.save(user);
        }
        return json(true, user, "Success");
    }

    @GetMapping
    public Map getView(HttpServletRequest request, Authentication authentication) {
        return json(true, ((PrincipalUser) authentication.getPrincipal()).getMap(), "Success");
    }
}
