package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Console;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Game;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.ConsoleRepository;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameRepository repo;

    @GetMapping()
    public List<Game> getGames() {return repo.findAll();}

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable int id) {
        Optional<Game> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {
        return repo.save(game);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
        repo.save(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        repo.deleteById(id);
    }

    @GetMapping("/studio/{studio}")
    public List<Game> getGameByStudio(@PathVariable String studio) {
        return repo.findByStudio(studio);
    }

    @GetMapping("/esrb/{esrb}")
    public List<Game> getGameByESRB(@PathVariable String esrbRating) {
        return repo.findByESRB(esrbRating);
    }

    @GetMapping("/title/{title}")
    public List<Game> getGameByTitle(@PathVariable String title) {
        return repo.findByESRB(title);
    }
}
