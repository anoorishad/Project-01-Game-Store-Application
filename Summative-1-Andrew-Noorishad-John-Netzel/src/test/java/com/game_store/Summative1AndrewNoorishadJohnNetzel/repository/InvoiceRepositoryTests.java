package com.game_store.Summative1AndrewNoorishadJohnNetzel.repository;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTests {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Before
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();

        invoiceRepository.save(new Invoice("Andrew","123 Street","Seattle","WA","12345","Console",1, BigDecimal.valueOf(199.99),5,BigDecimal.valueOf(199.99),BigDecimal.valueOf(10.99),BigDecimal.valueOf(14.99),BigDecimal.valueOf(130.99)));
        invoiceRepository.save(new Invoice("John","123 Street","Anchorage","AK","12345","Console",1, BigDecimal.valueOf(199.99),5,BigDecimal.valueOf(199.99),BigDecimal.valueOf(10.99),BigDecimal.valueOf(14.99),BigDecimal.valueOf(130.99)));
        invoiceRepository.save(new Invoice("Andrew","123 Street","Seattle","WA","12345","Console",1, BigDecimal.valueOf(199.99),5,BigDecimal.valueOf(199.99),BigDecimal.valueOf(10.99),BigDecimal.valueOf(14.99),BigDecimal.valueOf(130.99)));
    }

    @Test
    public void shouldFindInvoicesByName() {
        List<Invoice> andrewInvoices = invoiceRepository.findByName("Andrew");

        assertEquals(2,andrewInvoices.size());
    }
}