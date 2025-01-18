package org.sid.ebanking_backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebanking_backend.entities.AccountOperation;
import org.sid.ebanking_backend.exceptions.BalanceInsufficientException;
import org.sid.ebanking_backend.exceptions.BankingAccoundNotFoundException;
import org.sid.ebanking_backend.services.IBankAccounService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/operations")
public class OperationAccountController {
    IBankAccounService bankAccounService;

    @PostMapping("/debit/{account_id}")
    public ResponseEntity<?> saveDebitOperation(@PathVariable("account_id") String account_id,
                                                               @RequestBody AccountOperation accountOperationRequest,
                                                               UriComponentsBuilder ucb){
        try {
            AccountOperation debitOperationSaved = bankAccounService.debit(
                    account_id,
                    accountOperationRequest.getAmount(),
                    accountOperationRequest.getDescription()
            );

            URI location = ucb
                    .path("/operations/debit/{account_id}")
                    .buildAndExpand(debitOperationSaved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(debitOperationSaved);

        } catch (BankingAccoundNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank account not found" + e.getMessage());
        } catch (BalanceInsufficientException e) {
            return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE)
                    .body("the balance is insufficient" + e.getMessage());
        }

    }
}
