package com.poly.entities;

import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO{
    @JsonProperty("accountId")
    private int accountId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("fullName")
    private String fullName;

    @Column(name = "address")
    private String address;

    @JsonProperty("addressDetail")
    private String addressDetail;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("photo")
    private String photo;
}
