package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;


import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Console;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consoles")
public class ConsoleController {

    @Autowired
    ConsoleRepository repo;

    @GetMapping()
    public List<Console> getConsoles() {return repo.findAll();}

    @GetMapping("/{id}")
    public Console getConsoleById(@PathVariable int id) {
        Optional<Console> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody @Valid Console console) {
        return repo.save(console);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid Console console) {
        repo.save(console);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        repo.deleteById(id);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return repo.findByManufacturer(manufacturer);
    }
}
