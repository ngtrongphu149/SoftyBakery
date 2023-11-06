package com.poly.models;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "AccountRoles")
@Data
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountroleid")
    private int accountRoleId;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;
}
