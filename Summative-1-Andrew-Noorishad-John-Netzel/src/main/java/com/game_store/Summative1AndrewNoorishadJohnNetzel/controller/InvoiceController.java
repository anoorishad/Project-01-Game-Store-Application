package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;


import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Invoice;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    InvoiceRepository repo;

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
    public Invoice addInvoice(@RequestBody Invoice console) {
        return repo.save(console);
    }

    @GetMapping("/name/{name}")
    public List<Invoice> getInvoiceByCustomerName(@PathVariable String name) {
        return repo.findByCustomerName(name);
    }
}
