package com.poly.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.entities.Role;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "Accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails{
	@Id
    @Column(name = "accountid")
    private int accountId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "addressdetail")
    private String addressDetail;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "photo")
    private String photo;
    
    @Column(name = "admin")
    private Boolean admin;


    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Order> orders;
    
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Review> reviews;

	

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(admin ? "ROLE_ADMIN" : "ROLE_USER"));
        return list;
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
	public String toString() {
    	return "- - - - - - - - - - - - - - - - - - - - ** User Info ** - - - - - - - - - - - - - - - - - - - -\n"
		        + "Username: " + getUsername() + "\n"
		        + "Password: " + getPassword() + "\n"
		        + "Authorities: " + getAuthorities() + "\n"
        +      "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -";
    }
	public String toStringDetail() {
    	return "- - - - - - - - - - - - - - - - - - - - ** User Info ** - - - - - - - - - - - - - - - - - - - -\n"
    			+ "Id: " + getAccountId() + "\n"
				+ "PhoneNumber: " + getPhoneNumber() + "\n"
		        + "Username: " + getUsername() + "\n"
		        + "Fullname: " + getFullName() + "\n"
		        + "Email: " + getEmail() + "\n"
		        + "Address: " + getAddress() + "\n"
		        + "Password: " + getPassword() + "\n"
        		+ "Admin: " + getAuthorities() + "\n"
				+ "Photo: " + getPhoto() + "\n"
		                		
        +      "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -";
    }
    
    
    
    
}
