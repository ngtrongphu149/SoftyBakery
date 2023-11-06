package com.poly.models;

import jakarta.persistence.*;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.poly.models.AccountRole;
import com.poly.models.Review;
import com.poly.models.Comment;
import com.poly.models.Order;


@Entity
@Table(name = "Accounts", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Data
public class Account  implements UserDetails{
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "addressdetail")
    private String addressDetail;

    @Column(name = "birthday")
    private Date birthDay;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "photo")
    private String photo;

    @Column(name = "isbanned")
    private boolean isBanned;

    @Column(name = "reasonbanned")
    private String reasonBanned;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;
    
    @OneToMany(mappedBy = "account")
    private List<Review> reviews;

    @OneToMany(mappedBy = "account")
    private List<Comment> comments;

    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoles;
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
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
}
