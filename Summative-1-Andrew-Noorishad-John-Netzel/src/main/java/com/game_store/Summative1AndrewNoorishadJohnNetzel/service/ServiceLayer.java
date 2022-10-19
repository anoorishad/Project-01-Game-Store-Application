package com.game_store.Summative1AndrewNoorishadJohnNetzel.service;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Invoice;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.ProcessingFee;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.SalesTaxRate;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.ProcessingFeeRepository;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.TaxRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class ServiceLayer {
    private TaxRateRepository taxRateRepository;
    private ProcessingFeeRepository processingFeeRepository;

    @Autowired
    public ServiceLayer(TaxRateRepository taxRateRepository, ProcessingFeeRepository processingFeeRepository) {
        this.taxRateRepository = taxRateRepository;
        this.processingFeeRepository = processingFeeRepository;
    }

    public void calculateSalesTax(Invoice invoice) {
        String state = invoice.getState(); // Read state from invoice
        Optional<SalesTaxRate> taxRateRecord = taxRateRepository.findById(state); // Query DB for sales tax by state name
        if(!taxRateRecord.isPresent()) { // If no sales tax record is found...
            throw new RuntimeException("Cannot find sales tax rate for state: " + state);
        }
        BigDecimal taxRate = taxRateRecord.get().getRate(); // Read sales tax rate from record from sales tax table
        BigDecimal tax = invoice.getSubtotal().multiply(taxRate);
        invoice.setTax(tax); // Load value into invoice
    }

    public void calculateProcessingFee(Invoice invoice) {
        String productType = invoice.getItemType();
        Optional<ProcessingFee> processingFeeRecord = processingFeeRepository.findById(productType);
        if(!processingFeeRecord.isPresent()) {
            throw new RuntimeException("Cannot find processing for state: " + productType);
        }
        BigDecimal processingFee = processingFeeRecord.get().getFee();
        // Check for large (>10) orders
        if(invoice.getQuantity() > 10) {
            processingFee = processingFee.add(BigDecimal.valueOf(15.49));
        }
        invoice.setProcessingFee(processingFee); // Load value into invoice
    }
}
