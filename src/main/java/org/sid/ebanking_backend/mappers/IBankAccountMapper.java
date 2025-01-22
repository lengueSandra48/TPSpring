package org.sid.ebanking_backend.mappers;

import org.sid.ebanking_backend.dtos.AccountOperationDTO;
import org.sid.ebanking_backend.dtos.CurrentBankAccountDTO;
import org.sid.ebanking_backend.dtos.CustomerDTO;
import org.sid.ebanking_backend.dtos.SavingBankAccountDTO;
import org.sid.ebanking_backend.entities.AccountOperation;
import org.sid.ebanking_backend.entities.CurrentAccount;
import org.sid.ebanking_backend.entities.Customer;
import org.sid.ebanking_backend.entities.SavingAccount;

public interface IBankAccountMapper {
    // transfert des donnees de Customer vers CustomerDTO
    CustomerDTO fromCustomer(Customer customer);

    // transfert des donnees de CustomerDTO vers Customer
    Customer fromCustomerDTO(CustomerDTO customerDTO);

    SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingBankAccount);

    SavingAccount toSavingBankAccount(SavingBankAccountDTO savingBankAccountDTO);

    CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentBankAccount);

    CurrentAccount toCurrentBankAccount(CurrentBankAccountDTO currentBankAccountDTO);

    AccountOperationDTO fromAccountOperation(AccountOperation accountOperation);
}
