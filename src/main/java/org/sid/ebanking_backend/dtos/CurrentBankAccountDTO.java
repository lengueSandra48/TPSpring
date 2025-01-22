package org.sid.ebanking_backend.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebanking_backend.entities.AccountOperation;
import org.sid.ebanking_backend.entities.Customer;
import org.sid.ebanking_backend.enums.AccountStatus;

import java.util.Date;
import java.util.List;


@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    CustomerDTO customerDTO;
    private double overDraft;

}
