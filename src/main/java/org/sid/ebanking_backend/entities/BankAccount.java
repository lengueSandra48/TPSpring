package org.sid.ebanking_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.sid.ebanking_backend.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //stocke toutes les classes qui vont herites de cette classe dans une table
@DiscriminatorColumn(name = "TYPE", length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne //plusieurs comptes pour un client
    private Customer customer;  //un compte appartient a un client, relation : one to many
    @OneToMany(mappedBy = "bankAccount") //relation bidirectionnelle entre le compte et l'operation
    private List<AccountOperation> accountOperations;

}
