package transfer.money.conversion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import transfer.money.conversion.dtos.AccountDto;
import transfer.money.conversion.services.AccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
	@Autowired
    private AccountService accountService;

	@GetMapping("/account/{accountNumber}")
    public ResponseEntity<AccountDto> findAccount(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByAccountNumber(accountNumber));
    }
}
