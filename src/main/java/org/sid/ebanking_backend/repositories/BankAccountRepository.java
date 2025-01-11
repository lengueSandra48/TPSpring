package org.sid.ebanking_backend.repositories;

import org.sid.ebanking_backend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
