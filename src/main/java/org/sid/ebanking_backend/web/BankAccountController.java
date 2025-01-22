package org.sid.ebanking_backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebanking_backend.dtos.AccountOperationDTO;
import org.sid.ebanking_backend.dtos.BankAccountDTO;
import org.sid.ebanking_backend.entities.BankAccount;
import org.sid.ebanking_backend.entities.CurrentAccount;
import org.sid.ebanking_backend.exceptions.BankingAccoundNotFoundException;
import org.sid.ebanking_backend.exceptions.CustomerNotFoundException;
import org.sid.ebanking_backend.services.IBankAccounService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("accounts")
public class BankAccountController {
    IBankAccounService bankAccounService;

    @GetMapping("/{accountId}")
    public BankAccountDTO getBankAccount(String accountId) throws BankingAccoundNotFoundException {
        return bankAccounService.getBankAccount(accountId);
    }

    @GetMapping("/list")
    public List<BankAccountDTO> listAccounts() {
        return bankAccounService.bankAccountList();
    }

    @GetMapping("/{account_id}/history")
    public List<AccountOperationDTO> accountHistory(@PathVariable("account_id") String account_id) {
        return bankAccounService.accounthistory(account_id);
    }

//    @PostMapping("/currentAccounts/{client_id}")
//    public ResponseEntity<?> saveCurrentBankAccount(
//            @PathVariable("client_id")  Long client_id,
//            @RequestBody CurrentAccount currentAccount,
//            UriComponentsBuilder ucb) {
//
//        try {
//            CurrentAccount currentAccount1 = (CurrentAccount) bankAccounService.saveCurrentBankAccount(
//                    currentAccount.getBalance(),
//                    currentAccount.getOverDraft(),
//                    client_id
//            );
//
//            URI locationCurrentAccount = ucb
//                    .path("/account/currentAccounts/{client_id}")
//                    .buildAndExpand(currentAccount1.getId())
//                    .toUri();
//
//            return ResponseEntity.created(locationCurrentAccount).body(currentAccount1);
//
//        } catch (CustomerNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Customer not found: " + e.getMessage());
//        }
//    }




}
