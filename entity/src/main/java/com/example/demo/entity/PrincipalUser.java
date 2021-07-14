package com.example.demo.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrincipalUser extends org.springframework.security.core.userdetails.User {
    private int userId;
    private String name;
    private String email;
    private String username1;
    private String password1;
    private boolean isAdmin;
    private boolean isActive;

    public PrincipalUser(User user) {
        super(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.isAdmin() ? "ADMIN" : "USER")));
        userId = user.getId().intValue();
        name = user.getName();
        email = user.getEmail();
        username1 = user.getUsername();
        password1 = user.getPassword();
        isAdmin = user.isAdmin();
        isActive = user.isActive();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return this.username1;
    }

    @Override
    public String getPassword() {
        return this.password1;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public Map getMap() {
        Map m = new HashMap();
        m.put("id", userId);
        m.put("full_name", name);
        m.put("email", email);
        m.put("username", username1);
        m.put("is_active", isActive);
        return m;
    }
}
