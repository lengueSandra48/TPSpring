package org.sid.ebanking_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "customer")  //relation bidirectionnelle
    private List<BankAccount> bankAccounts; // un client peut avoir plusieurs comptes : relation one to many

}

