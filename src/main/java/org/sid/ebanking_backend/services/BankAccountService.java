package org.sid.ebanking_backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebanking_backend.entities.*;
import org.sid.ebanking_backend.enums.OperationType;
import org.sid.ebanking_backend.exceptions.BalanceInsufficientException;
import org.sid.ebanking_backend.exceptions.BankingAccoundNotFoundException;
import org.sid.ebanking_backend.exceptions.CustomerNotFoundException;
import org.sid.ebanking_backend.repositories.AccountOperationRepository;
import org.sid.ebanking_backend.repositories.BankAccountRepository;
import org.sid.ebanking_backend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor //Injection des dependances par le constructeur
@Slf4j //journalisation
public class BankAccountService implements IBankAccounService{

    CustomerRepository customerRepository;
    BankAccountRepository bankAccountRepository;
    AccountOperationRepository accountOperationRepository;
   // Logger log= (Logger) LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Customer saveCustomer(Customer customer) {
        //log.info("Saving new Customer");
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public BankAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);

        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);


        return savedBankAccount;
    }

    @Override
    public BankAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null){
            throw new CustomerNotFoundException("No customer found");
        }

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setInterestRate(interestRate);
        savingAccount.setBalance(initialBalance);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setCustomer(customer);

        SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
        return null;
    }

    @Override
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public BankAccount getBankAccount(String accountId) throws BankingAccoundNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if (bankAccount == null) {
            throw new BankingAccoundNotFoundException(" Bank account not found");
        }

        return bankAccount;
    }

    @Override
    public AccountOperation debit(String accountId, double amount, String description) throws BankingAccoundNotFoundException, BalanceInsufficientException {
        BankAccount bankAccount = getBankAccount(accountId);
        if (bankAccount.getBalance() < amount){
            throw new BalanceInsufficientException("Your balance is insufficient");
        }

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);

        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);

        return accountOperation;
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankingAccoundNotFoundException {
        BankAccount bankAccount = getBankAccount(accountId);

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setOperationDate(new Date());
        accountOperation.setDescription(description);

        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);


    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceInsufficientException, BankingAccoundNotFoundException {

        debit(accountIdDestination,amount, "transfer to "+accountIdDestination);
        credit(accountIdDestination,amount, "transfer from " + accountIdSource);

    }

    @Override
    public List<BankAccount> bankAccountList() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }


}
