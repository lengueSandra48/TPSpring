package org.sid.ebanking_backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebanking_backend.entities.BankAccount;
import org.sid.ebanking_backend.entities.CurrentAccount;
import org.sid.ebanking_backend.exceptions.CustomerNotFoundException;
import org.sid.ebanking_backend.services.IBankAccounService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("account")
public class BankAccountController {
    IBankAccounService bankAccounService;

    @PostMapping("/currentAccounts/{client_id}")
    public ResponseEntity<?> saveCurrentBankAccount(
            @PathVariable("client_id")  Long client_id,
            @RequestBody CurrentAccount currentAccount,
            UriComponentsBuilder ucb) {

        try {
            CurrentAccount currentAccount1 = (CurrentAccount) bankAccounService.saveCurrentBankAccount(
                    currentAccount.getBalance(),
                    currentAccount.getOverDraft(),
                    client_id
            );

            URI locationCurrentAccount = ucb
                    .path("/account/currentAccounts/{client_id}")
                    .buildAndExpand(currentAccount1.getId())
                    .toUri();

            return ResponseEntity.created(locationCurrentAccount).body(currentAccount1);

        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer not found: " + e.getMessage());
        }
    }



}
