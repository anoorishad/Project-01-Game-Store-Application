package com.game_store.Summative1AndrewNoorishadJohnNetzel.service;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.*;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.*;


import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.TransactionSystemException;


import javax.persistence.RollbackException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer serviceLayer;

    ConsoleRepository consoleRepository;
    GameRepository gameRepository;
    TShirtRepository tShirtRepository;
    ProcessingFeeRepository processingFeeRepository;
    TaxRateRepository taxRateRepository;
    InvoiceRepository invoiceRepository;

    Invoice invoice;
    Invoice invoice2;

    @Before
    public void setUp() throws Exception {
        setUpConsoleRepositoryMock();
        setUpGameRepositoryMock();
        setUpTShirtRepositoryMock();
        setUpProcessingFeeRepositoryMock();
        setUpTaxRateRepositoryMock();
        setUpInvoiceRepositoryMock();

        serviceLayer = new ServiceLayer(invoiceRepository,taxRateRepository,processingFeeRepository,gameRepository,consoleRepository,tShirtRepository);
    }

    private void setUpConsoleRepositoryMock() {
        consoleRepository= mock(ConsoleRepository.class);
        Console console = new Console();
        console.setId(1);
        console.setModel("Xbox Series X");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("32GB");
        console.setProcessor("5ghz");
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setQuantity(20);

        Console console2 = new Console();
        console2.setModel("Xbox Series X");
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("32GB");
        console2.setProcessor("5ghz");
        console2.setPrice(BigDecimal.valueOf(199.99));
        console2.setQuantity(10);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);

        doReturn(console).when(consoleRepository).save(console2);
        doReturn(Optional.of(console)).when(consoleRepository).findById(1);
        doReturn(consoleList).when(consoleRepository).findAll();

    }

    private void setUpGameRepositoryMock() {
        gameRepository= mock(GameRepository.class);
        Game game = new Game();
        game.setId(1);
        game.setTitle("Halo");
        game.setEsrbRating("M");
        game.setDescription("Play as the Master Chief");
        game.setPrice(BigDecimal.valueOf(49.99));
        game.setStudio("Bungie");
        game.setQuantity(5);

        Game game2 = new Game();
        game.setTitle("Halo");
        game.setEsrbRating("M");
        game.setDescription("Play as the Master Chief");
        game.setPrice(BigDecimal.valueOf(49.99));
        game.setStudio("Bungie");
        game.setQuantity(5);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        doReturn(game).when(gameRepository).save(game2);
        doReturn(Optional.of(game)).when(gameRepository).findById(1);
        doReturn(gameList).when(gameRepository).findAll();

    }

    private void setUpTShirtRepositoryMock(){
        tShirtRepository= mock(TShirtRepository.class);
        TShirt tShirt = new TShirt();
        tShirt.setId(1);
        tShirt.setSize("Large");
        tShirt.setColor("Black");
        tShirt.setDescription("Large Black T-Shirt");
        tShirt.setPrice(BigDecimal.valueOf(9.99));
        tShirt.setQuantity(10);

        TShirt tShirt2 = new TShirt();
        tShirt.setSize("Large");
        tShirt.setColor("Black");
        tShirt.setDescription("Large Black T-Shirt");
        tShirt.setPrice(BigDecimal.valueOf(9.99));
        tShirt.setQuantity(10);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt);

        doReturn(tShirt).when(tShirtRepository).save(tShirt2);
        doReturn(Optional.of(tShirt)).when(tShirtRepository).findById(1);
        doReturn(tShirtList).when(tShirtRepository).findAll();

    }

    private void setUpProcessingFeeRepositoryMock() {
        processingFeeRepository= mock(ProcessingFeeRepository.class);
        ProcessingFee consoleProcessingFee = new ProcessingFee();
        consoleProcessingFee.setProductType("Consoles");
        consoleProcessingFee.setFee(BigDecimal.valueOf(14.99));


        ProcessingFee tShirtProcessingFee = new ProcessingFee();
        tShirtProcessingFee.setProductType("T-shirts");
        tShirtProcessingFee.setFee(BigDecimal.valueOf(1.98));

        ProcessingFee gameProcessingFee = new ProcessingFee();
        gameProcessingFee.setProductType("Games");
        gameProcessingFee.setFee(BigDecimal.valueOf(1.98));

        doReturn(Optional.of(consoleProcessingFee)).when(processingFeeRepository).findById("Consoles");
        doReturn(Optional.of(tShirtProcessingFee)).when(processingFeeRepository).findById("T-shirts");
        doReturn(Optional.of(gameProcessingFee)).when(processingFeeRepository).findById("Games");
    }

    private void setUpTaxRateRepositoryMock() {
        taxRateRepository= mock(TaxRateRepository.class);
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("WA");
        salesTaxRate.setRate(BigDecimal.valueOf(0.06));

        doReturn(Optional.of(salesTaxRate)).when(taxRateRepository).findById("WA");
    }

    private void setUpInvoiceRepositoryMock() {
        invoiceRepository= mock(InvoiceRepository.class);
        invoice = new Invoice();
        invoice.setId(1);
        invoice.setName("Andrew");
        invoice.setStreet("123 Street");
        invoice.setCity("Seattle");
        invoice.setState("WA");
        invoice.setZipcode("12345");
        invoice.setItemType("Consoles");
        invoice.setItemId(1);
        invoice.setUnitPrice(BigDecimal.valueOf(199.99));
        invoice.setQuantity(5);
        invoice.setSubtotal(BigDecimal.valueOf(999.95));
        invoice.setTax(BigDecimal.valueOf(59.997));
        invoice.setProcessingFee(BigDecimal.valueOf(14.99));
        invoice.setTotal(BigDecimal.valueOf(1074.937));

        invoice2 = new Invoice();
        invoice2.setName("Andrew");
        invoice2.setStreet("123 Street");
        invoice2.setCity("Seattle");
        invoice2.setState("WA");
        invoice2.setZipcode("12345");
        invoice2.setItemType("Consoles");
        invoice2.setItemId(1);
        invoice2.setUnitPrice(BigDecimal.valueOf(199.99));
        invoice2.setQuantity(5);
        invoice2.setSubtotal(BigDecimal.valueOf(999.95));
        invoice2.setTax(BigDecimal.valueOf(59.997));
        invoice2.setProcessingFee(BigDecimal.valueOf(14.99));
        invoice2.setTotal(BigDecimal.valueOf(1074.937));

        doReturn(invoice).when(invoiceRepository).save(invoice2);
    }
    @Test
    public void shouldGetConsolePrice() {
        BigDecimal expectedResult = BigDecimal.valueOf(199.99);

        BigDecimal actualResult = consoleRepository.findById(1).get().getPrice();

        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void shouldGetGamePrice() {
        BigDecimal expectedResult = BigDecimal.valueOf(49.99);

        BigDecimal actualResult = gameRepository.findById(1).get().getPrice();

        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void shouldGetTShirtPrice() {
        BigDecimal expectedResult = BigDecimal.valueOf(9.99);

        BigDecimal actualResult = tShirtRepository.findById(1).get().getPrice();

        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shouldCalculateSalesTax() {
        BigDecimal expectedResult = BigDecimal.valueOf(59.997);

        Optional<SalesTaxRate> taxRateRecord = taxRateRepository.findById(invoice.getState());
        if(!taxRateRecord.isPresent()) { // If no sales tax record is found...
            throw new RuntimeException("Cannot find sales tax rate for state: " + invoice.getState());
        }
        BigDecimal taxRate = taxRateRecord.get().getRate();

        Double actualResultDouble = (invoice.getSubtotal().multiply(taxRate).doubleValue());

        BigDecimal actualResult = BigDecimal.valueOf(actualResultDouble);

        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shouldCalculateProcessingFee() {
        BigDecimal expectedResult = BigDecimal.valueOf(14.99);

        Optional<ProcessingFee> processingFeeRecord = processingFeeRepository.findById(invoice.getItemType());

        if(!processingFeeRecord.isPresent()) {
            throw new RuntimeException("Cannot find processing for product type: " + invoice.getItemType());
        }
        BigDecimal processingFee = processingFeeRecord.get().getFee();
        // Check for large (>10) orders
        if(invoice.getQuantity() > 10) {
            processingFee = processingFee.add(BigDecimal.valueOf(15.49));
        }
        invoice.setProcessingFee(processingFee); // Load value into invoice

        BigDecimal actualResult = invoice.getProcessingFee();


        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shouldAddAdditionalProcessingFeeIfQuantityIsGreaterThan10ForCalculateProcessingFee() {
        invoice.setQuantity(11);
        BigDecimal expectedResult = BigDecimal.valueOf(30.48);

        Optional<ProcessingFee> processingFeeRecord = processingFeeRepository.findById(invoice.getItemType());

        if(!processingFeeRecord.isPresent()) {
            throw new RuntimeException("Cannot find processing for product type: " + invoice.getItemType());
        }
        BigDecimal processingFee = processingFeeRecord.get().getFee();
        // Check for large (>10) orders
        if(invoice.getQuantity() > 10) {
            processingFee = processingFee.add(BigDecimal.valueOf(15.49));
        }
        invoice.setProcessingFee(processingFee); // Load value into invoice

        BigDecimal actualResult = invoice.getProcessingFee();


        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shouldCalculateSubTotal() {
        BigDecimal expectedResult = BigDecimal.valueOf(999.95);

        BigDecimal itemPrice = invoice.getUnitPrice();
        invoice.setUnitPrice(itemPrice);
        BigDecimal subtotal = itemPrice.multiply(BigDecimal.valueOf(invoice.getQuantity()));
        invoice.setSubtotal(subtotal);

        BigDecimal actualResult = invoice.getSubtotal();

        assertEquals(expectedResult,actualResult);
    }


    @Test
    public void shouldCalculateTotal() {
        BigDecimal expectedResult = BigDecimal.valueOf(1074.937);

        BigDecimal total = invoice.getSubtotal();
        total = total.add(invoice.getTax());
        total = total.add(invoice.getProcessingFee());
        invoice.setTotal(total);

        BigDecimal actualResult = invoice.getTotal();

        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shouldUpdateInStockQuantity() {
        Integer expectedResult = 15;

        switch(invoice.getItemType()) {
            case "Games":
                Optional<Game> game = gameRepository.findById(invoice.getItemId());
                if(game.isPresent()) {
                    Game actualGame = game.get();
                    actualGame.setQuantity(actualGame.getQuantity() - invoice.getQuantity());
                    gameRepository.save(actualGame);
                    break;
                }
                throw new RuntimeException("Game with ID of " + invoice.getItemId() + " not found!");
            case "Consoles":
                Optional<Console> console = consoleRepository.findById(invoice.getItemId());
                if(console.isPresent()) {
                    Console actualConsole = console.get();
                    actualConsole.setQuantity(actualConsole.getQuantity() - invoice.getQuantity());
                    consoleRepository.save(actualConsole);
                    break;
                }
                throw new RuntimeException("Console with ID of " + invoice.getItemId() + " not found!");
            case "T-shirts":
                Optional<TShirt> tShirt = tShirtRepository.findById(invoice.getItemId());
                if(tShirt.isPresent()) {
                    TShirt actualTShirt = tShirt.get();
                    actualTShirt.setQuantity(actualTShirt.getQuantity() - invoice.getQuantity());
                    tShirtRepository.save(actualTShirt);
                    break;
                }
                throw new RuntimeException("T-Shirt with ID of " + invoice.getItemId() + " not found!");
            default:
                throw new IllegalArgumentException("Invalid itemType: " + invoice.getItemType());
        }
        Optional<Console> optionalResult = consoleRepository.findById(invoice.getItemId());
        Integer actualResult = optionalResult.get().getQuantity();

        assertEquals(expectedResult,actualResult);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithInvalidStateForCalculateSalesTax() {
        invoice.setState("OT");

        Optional<SalesTaxRate> taxRateRecord = taxRateRepository.findById(invoice.getState());
        if(!taxRateRecord.isPresent()) { // If no sales tax record is found...
            throw new RuntimeException("Cannot find sales tax rate for state: " + invoice.getState());
        }
    }
    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithInvalidProductTypeForCalculateProcessingFee() {
        invoice.setItemType("Test");

        Optional<ProcessingFee> processingFeeRecord = processingFeeRepository.findById(invoice.getItemType());

        if(!processingFeeRecord.isPresent()) {
            throw new RuntimeException("Cannot find processing for product type: " + invoice.getItemType());
        }
        BigDecimal processingFee = processingFeeRecord.get().getFee();
        // Check for large (>10) orders
        if(invoice.getQuantity() > 10) {
            processingFee = processingFee.add(BigDecimal.valueOf(15.49));
        }
        invoice.setProcessingFee(processingFee); // Load value into invoice
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithInvalidItemTypeForUpdateInStockQuantity() {
        invoice.setItemType("Test");

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
    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithQuantityLargerThanAvailableForUpdateInStockQuantity() {
        invoice.setQuantity(100);

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