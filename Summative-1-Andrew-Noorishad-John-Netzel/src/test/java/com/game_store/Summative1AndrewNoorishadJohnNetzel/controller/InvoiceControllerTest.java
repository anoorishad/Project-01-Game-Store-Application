package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Invoice;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.InvoiceRepository;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InvoiceRepository invoiceRepository;
    @MockBean
    ServiceLayer serviceLayer;


    private ObjectMapper mapper = new ObjectMapper();

    Invoice inputAndrew;
    Invoice outputAndrew;
    Invoice outputAndrew2;
    Invoice outputJohn;


    List<Invoice> andrewInvoices;
    List<Invoice> allInvoices;

    @Before
    public void setUp() throws Exception {
        inputAndrew = new Invoice("Andrew","123 Street","Seattle","WA","12345","Console",1, BigDecimal.valueOf(199.99),5,BigDecimal.valueOf(199.99),BigDecimal.valueOf(10.99),BigDecimal.valueOf(14.99),BigDecimal.valueOf(130.99));
        outputAndrew = new Invoice("Andrew","123 Street","Seattle","WA","12345","Console",1, BigDecimal.valueOf(199.99),5,BigDecimal.valueOf(199.99),BigDecimal.valueOf(10.99),BigDecimal.valueOf(14.99),BigDecimal.valueOf(130.99));
        outputAndrew.setId(1);
        outputAndrew2 = new Invoice("Andrew","123 Street","Seattle","WA","12345","Console",1, BigDecimal.valueOf(199.99),5,BigDecimal.valueOf(199.99),BigDecimal.valueOf(10.99),BigDecimal.valueOf(14.99),BigDecimal.valueOf(130.99));
        outputAndrew2.setId(2);
        outputJohn = new Invoice("Andrew","123 Street","Seattle","WA","12345","Console",1, BigDecimal.valueOf(199.99),5,BigDecimal.valueOf(199.99),BigDecimal.valueOf(10.99),BigDecimal.valueOf(14.99),BigDecimal.valueOf(130.99));
        outputJohn.setId(3);


        andrewInvoices = new ArrayList<>(Arrays.asList(
                outputAndrew,
                outputAndrew2
        ));

        allInvoices = new ArrayList<>(Arrays.asList(
                outputAndrew,
                outputAndrew2,
                outputJohn
        ));

        doReturn(outputAndrew).when(serviceLayer).addInvoice(inputAndrew);
        doReturn(Optional.of(outputAndrew)).when(invoiceRepository).findById(1);
        doReturn(allInvoices).when(invoiceRepository).findAll();
        doReturn(andrewInvoices).when(invoiceRepository).findByName("Andrew");

    }

    @Test
    public void shouldAddInvoiceOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputAndrew);
        String outputJson = mapper.writeValueAsString(outputAndrew);

        mockMvc.perform(post("/invoices")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetInvoiceById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputAndrew);

        mockMvc.perform(get("/invoices/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetInvoicesByCustomerName() throws Exception {
        String outputJson = mapper.writeValueAsString(andrewInvoices);

        mockMvc.perform(get("/invoices/name/Andrew"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllInvoices() throws Exception {
        String outputJson = mapper.writeValueAsString(allInvoices);

        mockMvc.perform(get("/invoices"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
}