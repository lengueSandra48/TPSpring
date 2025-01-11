package org.sid.ebanking_backend.repositories;

import org.sid.ebanking_backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
