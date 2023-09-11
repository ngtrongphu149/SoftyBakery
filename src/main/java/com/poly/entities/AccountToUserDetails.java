package com.poly.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountToUserDetails implements UserDetails{
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public AccountToUserDetails(Account account) {
		this.username = account.getUsername();
		this.password = account.getPassword();
		this.authorities = (List<GrantedAuthority>) account.getAuthorities();
	}
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
	@Override
    public String getUsername() {
        return username;
    }
	@Override
    public String getPassword() {
        return password;
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
