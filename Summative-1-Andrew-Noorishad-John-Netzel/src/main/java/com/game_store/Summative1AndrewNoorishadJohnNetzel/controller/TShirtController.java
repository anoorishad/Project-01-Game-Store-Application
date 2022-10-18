package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;


import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.TShirt;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tshirts")
public class TShirtController {

    @Autowired
    TShirtRepository repo;

    @GetMapping()
    public List<TShirt> getTShirts() {return repo.findAll();}

    @GetMapping("/{id}")
    public TShirt getTShirtById(@PathVariable int id) {
        Optional<TShirt> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody @Valid TShirt tShirt) {
        return repo.save(tShirt);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody @Valid TShirt tShirt) {
        repo.save(tShirt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id) {
        repo.deleteById(id);
    }

    @GetMapping("/color/{color}")
    public List<TShirt> getTShirtByColor(@PathVariable String color) {
        return repo.findByColor(color);
    }

    @GetMapping("/size/{size}")
    public List<TShirt> getTShirtBySize(@PathVariable String size) {
        return repo.findBySize(size);
    }
}
