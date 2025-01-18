package org.sid.ebanking_backend.services;

import org.sid.ebanking_backend.entities.AccountOperation;
import org.sid.ebanking_backend.entities.BankAccount;
import org.sid.ebanking_backend.entities.Customer;
import org.sid.ebanking_backend.exceptions.BalanceInsufficientException;
import org.sid.ebanking_backend.exceptions.BankingAccoundNotFoundException;
import org.sid.ebanking_backend.exceptions.CustomerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IBankAccounService {
     Customer saveCustomer(Customer customer);
     BankAccount saveCurrentBankAccount(double initialBalance,double overDraft, Long customerId) throws CustomerNotFoundException;
     BankAccount saveSavingBankAccount(double initialBalance, double interestRate,  Long customerId) throws CustomerNotFoundException;
     List<Customer> listCustomers();
     BankAccount getBankAccount(String accountId) throws BankingAccoundNotFoundException;
     AccountOperation debit(String accountId, double amount, String description) throws BankingAccoundNotFoundException, BalanceInsufficientException;
     void credit(String accountId, double amount, String description) throws BankingAccoundNotFoundException;
     void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceInsufficientException, BankingAccoundNotFoundException;
     List<BankAccount> bankAccountList();

     Optional<Customer> findCustomerById(Long id);

}