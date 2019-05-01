package com.orangelabs.RestaurantDemo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthenticatedProfile implements Authentication {

    private final String email;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtAuthenticatedProfile(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }
    
    public String getemail() {
        return email;
    }

	@Override
	public String getName() {
		return null;
	}
}

