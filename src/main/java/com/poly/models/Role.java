package com.poly.models;

import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "Roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private int roleId;

    @Column(name = "rolename")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<AccountRole> AccountRoles;
}
