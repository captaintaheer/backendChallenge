package com.turing.backendChallenge.Repo;

import com.turing.backendChallenge.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepo extends JpaRepository<Cart, Integer> {
}
