package com.game_store.Summative1AndrewNoorishadJohnNetzel.service;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.*;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.*;
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
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TShirtRepository tShirtRepository;

    @Autowired
    public ServiceLayer(TaxRateRepository taxRateRepository, ProcessingFeeRepository processingFeeRepository,
                        GameRepository gameRepository, ConsoleRepository consoleRepository,
                        TShirtRepository tShirtRepository) {
        this.taxRateRepository = taxRateRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tShirtRepository = tShirtRepository;
    }

    public void calculateSubtotal(Invoice invoice) {
        BigDecimal subtotal = getItemPrice(invoice.getItemType(), invoice.getItemId());
        subtotal = subtotal.multiply(BigDecimal.valueOf(invoice.getQuantity()));
        invoice.setSubtotal(subtotal);
    }

    private BigDecimal getItemPrice(String itemType, Integer itemId) {
        switch(itemType) {
            case "Game":
                Optional<Game> game = gameRepository.findById(itemId);
                if(game.isPresent()) {
                    return game.get().getPrice();
                }
                throw new RuntimeException("Game with ID of " + itemId + " not found!");
            case "Console":
                Optional<Console> console = consoleRepository.findById(itemId);
                if(console.isPresent()) {
                    return console.get().getPrice();
                }
                throw new RuntimeException("Console with ID of " + itemId + " not found!");
            case "T-Shirt":
                Optional<TShirt> tShirt = tShirtRepository.findById(itemId);
                if(tShirt.isPresent()) {
                    return tShirt.get().getPrice();
                }
                throw new RuntimeException("T-Shirt with ID of " + itemId + " not found!");
            default:
                throw new IllegalArgumentException("Invalid itemType: " + itemType);
        }
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

    public void calculateTotal(Invoice invoice) {
        BigDecimal total = invoice.getSubtotal();
        total = total.add(invoice.getTax());
        total = total.add(invoice.getProcessingFee());
        invoice.setTotal(total);
    }

    //public void updateInStockQuantity
}
