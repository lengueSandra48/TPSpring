package org.sid.ebanking_backend.services;

import org.sid.ebanking_backend.dtos.*;
import org.sid.ebanking_backend.entities.AccountOperation;
import org.sid.ebanking_backend.exceptions.BalanceInsufficientException;
import org.sid.ebanking_backend.exceptions.BankingAccoundNotFoundException;
import org.sid.ebanking_backend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface IBankAccounService {
     CustomerDTO saveCustomer(CustomerDTO customer);
     CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
     SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
     List<CustomerDTO> listCustomers();
     BankAccountDTO getBankAccount(String accountId) throws BankingAccoundNotFoundException;
     AccountOperation debit(String accountId, double amount, String description) throws BankingAccoundNotFoundException, BalanceInsufficientException;
     void credit(String accountId, double amount, String description) throws BankingAccoundNotFoundException;
     void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceInsufficientException, BankingAccoundNotFoundException;
     List<BankAccountDTO> bankAccountList();

     CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException;

     CustomerDTO updateCustomer(CustomerDTO customerDTO);

     void delete(Long customer_id);

     List<AccountOperationDTO> accounthistory(String account_id);
}