package com.poly.restcontrollers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dto.AccountDTO;
import com.poly.models.Account;
import com.poly.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/account")
public class AccountRestController {
    @Autowired
    AccountDAO aDAO;
	@Autowired
	AccountService accountService;
	ObjectMapper ObjectMapper = new ObjectMapper();
	@GetMapping
	public ResponseEntity<List<AccountDTO>> page() {
		List<AccountDTO> aDTOs = aDAO.findAll().stream()
				.map(a -> {
					AccountDTO aDTO = new AccountDTO();
						aDTO.setAccount(a);
					return aDTO;
				})
				.collect(Collectors.toList());

		return ResponseEntity.ok(aDTOs);
	}
	@PostMapping()
	public ResponseEntity<Account> post(@RequestBody Account account) {
		return ResponseEntity.ok(accountService.add(account));
	}

	@PutMapping("/{username}")
	public ResponseEntity<Account> put(@RequestBody Account account) {
		return ResponseEntity.ok(accountService.update(account));
	}

	@DeleteMapping("/{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.delete(username);
	}
}

