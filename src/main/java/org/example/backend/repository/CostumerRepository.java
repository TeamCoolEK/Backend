package org.example.backend.repository;

import org.example.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<Customer, Integer> {

    Customer findByPhoneNr(String phoneNr);
}
