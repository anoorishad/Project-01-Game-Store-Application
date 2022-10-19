package com.game_store.Summative1AndrewNoorishadJohnNetzel.repository;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Invoice;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxRateRepository extends JpaRepository<SalesTaxRate, Integer> {
    List<Invoice> findByState(String state);
}
