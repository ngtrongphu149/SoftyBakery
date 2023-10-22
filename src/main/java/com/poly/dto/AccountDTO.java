package com.poly.dto;

import com.poly.models.Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO extends Account{
	public AccountDTO(Account account) {
		super.setUsername(account.getUsername());
		super.setPassword(account.getPassword());
		super.setFullName(account.getFullName());
		
	}
}
