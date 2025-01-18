package org.sid.ebanking_backend.repositories;

import org.sid.ebanking_backend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    //List<AccountOperation> findByOperationDate(LocalDate operationDate);
}
