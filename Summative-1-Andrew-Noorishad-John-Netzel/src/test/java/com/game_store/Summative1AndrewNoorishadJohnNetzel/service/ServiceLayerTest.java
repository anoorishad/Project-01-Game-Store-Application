package com.game_store.Summative1AndrewNoorishadJohnNetzel.service;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.*;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.*;
import org.junit.Before;
import org.junit.Test;

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
        console.setQuantity(10);

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
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setName("Andrew");
        invoice.setStreet("123 Street");
        invoice.setCity("Seattle");
        invoice.setState("WA");
        invoice.setZipcode("12345");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setUnitPrice(BigDecimal.valueOf(199.99));
        invoice.setQuantity(5);
        invoice.setSubtotal(BigDecimal.valueOf(199.99));
        invoice.setTax(BigDecimal.valueOf(11.99));
        invoice.setProcessingFee(BigDecimal.valueOf(14.99));
        invoice.setTotal(BigDecimal.valueOf(226.97));

        Invoice invoice2 = new Invoice();
        invoice.setName("Andrew");
        invoice.setStreet("123 Street");
        invoice.setCity("Seattle");
        invoice.setState("WA");
        invoice.setZipcode("12345");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setUnitPrice(BigDecimal.valueOf(199.99));
        invoice.setQuantity(5);
        invoice.setSubtotal(BigDecimal.valueOf(199.99));
        invoice.setTax(BigDecimal.valueOf(11.99));
        invoice.setProcessingFee(BigDecimal.valueOf(14.99));
        invoice.setTotal(BigDecimal.valueOf(226.97));

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
}