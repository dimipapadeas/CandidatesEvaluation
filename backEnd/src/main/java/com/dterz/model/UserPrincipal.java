package com.dterz.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {
	
	private final User user;
	
	public UserPrincipal(User user) {
		this.user = user;
	}
	
	public long getUserId() {
		return this.user.getId();
	}
	
	public boolean isSuperAdmin() {
		return this.user.isSuperAdmin();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return this.user.getPass();
	}
	
	@Override
	public String getUsername() {
		return this.user.getUserName();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
