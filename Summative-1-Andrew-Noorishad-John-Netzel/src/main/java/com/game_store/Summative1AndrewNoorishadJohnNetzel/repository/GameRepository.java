package com.game_store.Summative1AndrewNoorishadJohnNetzel.repository;


import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findByStudio(String studio);
    List<Game> findByEsrbRating(String esrbRating);
    List<Game> findByTitle(String title);
}
