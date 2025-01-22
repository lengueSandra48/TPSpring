package org.sid.ebanking_backend.mappers;

import org.sid.ebanking_backend.dtos.AccountOperationDTO;
import org.sid.ebanking_backend.dtos.CurrentBankAccountDTO;
import org.sid.ebanking_backend.dtos.CustomerDTO;
import org.sid.ebanking_backend.dtos.SavingBankAccountDTO;
import org.sid.ebanking_backend.entities.AccountOperation;
import org.sid.ebanking_backend.entities.CurrentAccount;
import org.sid.ebanking_backend.entities.Customer;
import org.sid.ebanking_backend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

//mapStruct
// Le mappers transfer les donnees d'un objet vers un autre
@Service
public class BankAccountMapperImpl implements IBankAccountMapper{
    // transfert des donnees de Customer vers CustomerDTO
    @Override
    public CustomerDTO fromCustomer(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        return customerDTO;
    }

   // transfert des donnees de CustomerDTO vers Customer
   @Override
   public Customer fromCustomerDTO(CustomerDTO customerDTO){
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    @Override
    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingBankAccount){
        if ( savingBankAccount == null ) {
            return null;
        }

        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingBankAccount, savingBankAccountDTO);

        return savingBankAccountDTO;
    }

    @Override
    public SavingAccount toSavingBankAccount(SavingBankAccountDTO savingBankAccountDTO){
        if ( savingBankAccountDTO == null ) {
            return null;
        }

        SavingAccount savingBankAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingBankAccount);
        return savingBankAccount;
    }

    @Override
    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentBankAccount){
        if ( currentBankAccount == null ) {
            return null;
        }

        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentBankAccount, currentBankAccountDTO);

        return currentBankAccountDTO;
    }
    @Override
    public CurrentAccount toCurrentBankAccount(CurrentBankAccountDTO currentBankAccountDTO){
        if ( currentBankAccountDTO == null ) {
            return null;
        }

        CurrentAccount currentBankAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentBankAccount);
        return currentBankAccount;
    }

    @Override
    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        if ( accountOperation == null ) {
            return null;
        }

        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);

        return accountOperationDTO;
    }


}
