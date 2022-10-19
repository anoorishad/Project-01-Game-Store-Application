package com.game_store.Summative1AndrewNoorishadJohnNetzel.service;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Invoice;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.SalesTaxRate;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.TaxRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SalesTaxServiceLayer {
    private TaxRateRepository taxRateRepository;

    @Autowired
    public SalesTaxServiceLayer(TaxRateRepository taxRateRepository) {
        this.taxRateRepository = taxRateRepository;
    }

    @Transactional
    public Invoice calculateSalesTax(Invoice invoice) {
        String state = invoice.getState(); // Read state from invoice
        Optional<SalesTaxRate> taxRateRecord = taxRateRepository.findById(state); // Query DB for sales tax by state name
        if(!taxRateRecord.isPresent()) { // If no sales tax record is found...
            throw new RuntimeException("Cannot find sales tax rate for state: " + state);
        }
        BigDecimal taxRate = taxRateRecord.get().getRate(); // Read sales tax rate from record from sales tax table
        BigDecimal tax = invoice.getSubtotal().multiply(taxRate);
        invoice.setTax(tax); // Load value into invoice
        return invoice;
    }
}
