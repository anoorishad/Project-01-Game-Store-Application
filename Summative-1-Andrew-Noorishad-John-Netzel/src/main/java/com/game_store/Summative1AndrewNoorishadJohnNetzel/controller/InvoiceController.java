package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;


import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Invoice;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.InvoiceRepository;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    InvoiceRepository repo;

    @Autowired
    private ServiceLayer serviceLayer;

    @GetMapping()
    public List<Invoice> getInvoices() {return repo.findAll();}

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable int id) {
        Optional<Invoice> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody @Valid Invoice invoice) {
        return serviceLayer.addInvoice(invoice);
    }

    @GetMapping("/name/{name}")
    public List<Invoice> getInvoiceByCustomerName(@PathVariable String name) {
        return repo.findByName(name);
    }
}
