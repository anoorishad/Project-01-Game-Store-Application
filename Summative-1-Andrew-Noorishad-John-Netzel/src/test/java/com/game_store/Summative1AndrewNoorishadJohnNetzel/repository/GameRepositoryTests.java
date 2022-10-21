package com.game_store.Summative1AndrewNoorishadJohnNetzel.repository;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Game;
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
public class GameRepositoryTests {

    @Autowired
    GameRepository gameRepository;

    @Before
    public void setUp() throws Exception{
        gameRepository.deleteAll();

        gameRepository.save(new Game("Halo","M","Play as the Master Chief", BigDecimal.valueOf(49.99),"Bungie",5));
        gameRepository.save(new Game("Halo 2","M","Play as the Master Chief again", BigDecimal.valueOf(54.99),"Bungie",5));
        gameRepository.save(new Game("Destiny","T","Play as a Guardian", BigDecimal.valueOf(59.99),"Bungie",5));
    }

    @Test
    public void shouldFindGamesByStudio() {
        List<Game> bungieGames = gameRepository.findByStudio("Bungie");

        assertEquals(3,bungieGames.size());
    }

    @Test
    public void shouldFindGamesByEsrbRating() {
        List<Game> ratedMGames = gameRepository.findByEsrbRating("M");

        assertEquals(2,ratedMGames.size());
    }

    @Test
    public void shouldFindByTitle() {
        List<Game> haloGame = gameRepository.findByTitle("Halo");

        assertEquals(1,haloGame.size());
    }
}