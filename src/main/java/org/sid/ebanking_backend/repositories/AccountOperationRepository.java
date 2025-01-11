package org.sid.ebanking_backend.repositories;

import org.sid.ebanking_backend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
}
