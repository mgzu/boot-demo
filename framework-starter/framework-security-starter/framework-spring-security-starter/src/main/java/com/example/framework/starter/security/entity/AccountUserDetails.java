package com.example.framework.starter.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author MaGuangZu
 * @since 2024-05-24
 */
@Setter
@Getter
public class AccountUserDetails implements UserDetails {
	private String password;
	private String username;
	private Boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE.equals(this.enabled);
	}

	@Override
	public boolean isAccountNonLocked() {
		return Boolean.TRUE.equals(this.enabled);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE.equals(this.enabled);
	}

	@Override
	public boolean isEnabled() {
		return Boolean.TRUE.equals(this.enabled);
	}
}
