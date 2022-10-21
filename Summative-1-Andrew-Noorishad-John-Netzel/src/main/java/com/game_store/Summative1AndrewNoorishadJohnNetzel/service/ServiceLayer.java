package com.game_store.Summative1AndrewNoorishadJohnNetzel.service;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.*;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class ServiceLayer {
    private InvoiceRepository invoiceRepository;
    private TaxRateRepository taxRateRepository;
    private ProcessingFeeRepository processingFeeRepository;
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TShirtRepository tShirtRepository;

    @Autowired
    public ServiceLayer(InvoiceRepository invoiceRepository, TaxRateRepository taxRateRepository, ProcessingFeeRepository processingFeeRepository, GameRepository gameRepository, ConsoleRepository consoleRepository, TShirtRepository tShirtRepository) {
        this.invoiceRepository = invoiceRepository;
        this.taxRateRepository = taxRateRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tShirtRepository = tShirtRepository;
    }

    public Invoice addInvoice(@Valid Invoice invoice){
        setFinalInvoice(invoice);
        System.out.println(invoice.getSubtotal());
        return invoiceRepository.save(invoice);
    }

    private void setFinalInvoice(Invoice invoice) {
        calculateSubtotal(invoice);
        calculateSalesTax(invoice);
        calculateProcessingFee(invoice);
        calculateTotal(invoice);
        updateInStockQuantity(invoice);

    }

    private void calculateSubtotal(Invoice invoice) {
        BigDecimal itemPrice = getItemPrice(invoice.getItemType(), invoice.getItemId());
        invoice.setUnitPrice(itemPrice);
        BigDecimal subtotal = itemPrice.multiply(BigDecimal.valueOf(invoice.getQuantity()));
        invoice.setSubtotal(subtotal);
    }

    private BigDecimal getItemPrice(String itemType, Integer itemId) {
        switch(itemType) {
            case "Games":
                Optional<Game> game = gameRepository.findById(itemId);
                if(game.isPresent()) {
                    return game.get().getPrice();
                }
                throw new RuntimeException("Game with ID of " + itemId + " not found!");
            case "Consoles":
                Optional<Console> console = consoleRepository.findById(itemId);
                if(console.isPresent()) {
                    return console.get().getPrice();
                }
                throw new RuntimeException("Console with ID of " + itemId + " not found!");
            case "T-shirts":
                Optional<TShirt> tShirt = tShirtRepository.findById(itemId);
                if(tShirt.isPresent()) {
                    return tShirt.get().getPrice();
                }
                throw new RuntimeException("T-Shirt with ID of " + itemId + " not found!");
            default:
                throw new IllegalArgumentException("Invalid itemType: " + itemType);
        }
    }

    private void calculateSalesTax(Invoice invoice) {
        String state = invoice.getState(); // Read state from invoice
        Optional<SalesTaxRate> taxRateRecord = taxRateRepository.findById(state); // Query DB for sales tax by state name
        if(!taxRateRecord.isPresent()) { // If no sales tax record is found...
            throw new RuntimeException("Cannot find sales tax rate for state: " + state);
        }
        BigDecimal taxRate = taxRateRecord.get().getRate(); // Read sales tax rate from record from sales tax table
        BigDecimal tax = invoice.getSubtotal().multiply(taxRate);
        invoice.setTax(tax); // Load value into invoice
    }

    private void calculateProcessingFee(Invoice invoice) {
        String productType = invoice.getItemType();
        Optional<ProcessingFee> processingFeeRecord = processingFeeRepository.findById(productType);
        if(!processingFeeRecord.isPresent()) {
            throw new RuntimeException("Cannot find processing for product type: " + productType);
        }
        BigDecimal processingFee = processingFeeRecord.get().getFee();
        // Check for large (>10) orders
        if(invoice.getQuantity() > 10) {
            processingFee = processingFee.add(BigDecimal.valueOf(15.49));
        }
        invoice.setProcessingFee(processingFee); // Load value into invoice
    }

    private void calculateTotal(Invoice invoice) {
        BigDecimal total = invoice.getSubtotal();
        total = total.add(invoice.getTax());
        total = total.add(invoice.getProcessingFee());
        invoice.setTotal(total);
    }

    private void updateInStockQuantity(Invoice invoice) {
        switch(invoice.getItemType()) {
            case "Games":
                Optional<Game> game = gameRepository.findById(invoice.getItemId());
                if(game.isPresent()) {
                    Game actualGame = game.get();
                    actualGame.setQuantity(actualGame.getQuantity() - invoice.getQuantity());
                    gameRepository.save(actualGame);
                    return;
                }
                throw new RuntimeException("Game with ID of " + invoice.getItemId() + " not found!");
            case "Consoles":
                Optional<Console> console = consoleRepository.findById(invoice.getItemId());
                if(console.isPresent()) {
                    Console actualConsole = console.get();
                    actualConsole.setQuantity(actualConsole.getQuantity() - invoice.getQuantity());
                    consoleRepository.save(actualConsole);
                    return;
                }
                throw new RuntimeException("Console with ID of " + invoice.getItemId() + " not found!");
            case "T-shirts":
                Optional<TShirt> tShirt = tShirtRepository.findById(invoice.getItemId());
                if(tShirt.isPresent()) {
                    TShirt actualTShirt = tShirt.get();
                    actualTShirt.setQuantity(actualTShirt.getQuantity() - invoice.getQuantity());
                    tShirtRepository.save(actualTShirt);
                    return;
                }
                throw new RuntimeException("T-Shirt with ID of " + invoice.getItemId() + " not found!");
            default:
                throw new IllegalArgumentException("Invalid itemType: " + invoice.getItemType());
        }
    }
}
