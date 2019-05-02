package com.orangelabs.RestaurantDemo.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtTokenResponse {
	private String token;
	  private String type = "Bearer";
	  private String email;
	  private Collection<? extends GrantedAuthority> authorities;
	 
	  public JwtTokenResponse(String accessToken, String email, Collection<? extends GrantedAuthority> authorities) {
	    this.token = accessToken;
	    this.email = email;
	    this.authorities = authorities;
	  }
	 
	  public String getAccessToken() {
	    return token;
	  }
	 
	  public void setAccessToken(String accessToken) {
	    this.token = accessToken;
	  }
	 
	  public String getTokenType() {
	    return type;
	  }
	 
	  public void setTokenType(String tokenType) {
	    this.type = tokenType;
	  }
	 
	  public String getEmail() {
	    return email;
	  }
	 
	  public void setEmail(String email) {
	    this.email = email;
	  }
	  
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

}
