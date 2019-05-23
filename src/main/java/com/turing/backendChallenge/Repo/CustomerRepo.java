package com.turing.backendChallenge.Repo;

import com.turing.backendChallenge.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo  extends JpaRepository<Customer, Integer> {

}
