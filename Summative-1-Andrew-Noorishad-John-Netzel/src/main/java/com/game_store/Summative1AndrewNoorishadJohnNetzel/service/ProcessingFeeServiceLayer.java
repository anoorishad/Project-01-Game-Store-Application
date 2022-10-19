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
public class ProcessingFeeServiceLayer {
    private ProcessingFeeRepository processingFeeRepository;

    @Autowired
    public ProcessingFeeServiceLayer(ProcessingFeeRepository taxRateRepository) {
        this.processingFeeRepository = taxRateRepository;
    }

    @Transactional
    public Invoice calculateSalesTax(Invoice invoice) {
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
        return invoice;
    }
}
