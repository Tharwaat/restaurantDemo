package com.orangelabs.RestaurantDemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.orangelabs.RestaurantDemo.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtAuthenticatedProfile implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final String email;
    private Collection<? extends GrantedAuthority> userAuthorization;

    public JwtAuthenticatedProfile(String email, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.userAuthorization = authorities;
    }
    
    public static JwtAuthenticatedProfile build(UserEntity user) {
    	List<GrantedAuthority> authorities = new ArrayList();    	
    	authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
 
        return new JwtAuthenticatedProfile(
                user.getEmail(),
                authorities
        );
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return userAuthorization;
	}
	
    public String getemail() {
        return email;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}

