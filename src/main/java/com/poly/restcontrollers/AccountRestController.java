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
<<<<<<< HEAD
=======
import com.poly.dto.ProductDTO;
>>>>>>> 9107b44f7865bc2b6c0edeecd1263c5b8a424223
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
<<<<<<< HEAD
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

=======
    // private Account userInfo = new Account();
    // @GetMapping
    // public ResponseEntity<Account> user() {
    // 	userInfo = getAccountAuth();
    //     if (userInfo != null) {
    //     	userInfo = getAccountAuth();
    //     	return ResponseEntity.ok(userInfo);
    //     } else {
    //         return null;
    //     }
    // }
	// @GetMapping("/findAll")
	// public String FindAll() throws IOException {
	// 	return ObjectMapper.writeValueAsString(aDAO.findAll());
	// }
	// @GetMapping("/find/{username}")
	// public Account findByUsername(@PathVariable("username") String username) {
	// 	return aDAO.findById(username).orElse(null);
	// }

	// @PutMapping()
	// public ResponseEntity<Account> editProfile(Model model,@RequestBody Account user) {
	// 	user.setPassword(PasswordUtil.encode(user.getPassword()));
	// 	aDAO.save(user);
	// 	return ResponseEntity.ok(user);
	// }


	// public Account getAccountAuth() {
	// 	return accountService.getAccountAuth();
	// }
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

>>>>>>> 9107b44f7865bc2b6c0edeecd1263c5b8a424223
	@DeleteMapping("/{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.delete(username);
	}
}

