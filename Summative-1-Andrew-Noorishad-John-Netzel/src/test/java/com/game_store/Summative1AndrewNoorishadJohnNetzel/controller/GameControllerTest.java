package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Game;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.GameRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GameRepository gameRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Game inputHalo;
    Game outputHalo;
    Game outputHalo2;
    Game outputDestiny;

    List<Game> bungieGames;
    List<Game> mRatedGames;
    List<Game> haloGame;
    List<Game> allGames;

    @Before
    public void setUp() throws Exception {
        inputHalo = new Game("Halo","M","Play as the Master Chief", BigDecimal.valueOf(49.99),"Bungie",5);
        outputHalo = new Game("Halo","M","Play as the Master Chief", BigDecimal.valueOf(49.99),"Bungie",5);
        outputHalo.setId(1);
        outputHalo2 = new Game("Halo 2","M","Play as the Master Chief again", BigDecimal.valueOf(54.99),"Bungie",5);
        outputHalo2.setId(2);
        outputDestiny = new Game("Destiny","T","Play as a Guardian", BigDecimal.valueOf(59.99),"Bungie",5);
        outputDestiny.setId(3);

        bungieGames = new ArrayList<>(Arrays.asList(
                outputHalo,
                outputHalo2
        ));

        mRatedGames = new ArrayList<>(Arrays.asList(
                outputHalo,
                outputHalo2
        ));
        haloGame = new ArrayList<>(Arrays.asList(
                outputHalo
        ));

        allGames = new ArrayList<>(Arrays.asList(
                outputHalo,
                outputHalo2,
                outputDestiny
        ));

        doReturn(outputHalo).when(gameRepository).save(inputHalo);
        doReturn(Optional.of(outputHalo)).when(gameRepository).findById(1);
        doReturn(allGames).when(gameRepository).findAll();
        doReturn(bungieGames).when(gameRepository).findByStudio("Bungie");
        doReturn(mRatedGames).when(gameRepository).findByEsrbRating("M");
        doReturn(haloGame).when(gameRepository).findByTitle("Halo");

    }

    @Test
    public void shouldAddGameOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputHalo);
        String outputJson = mapper.writeValueAsString(outputHalo);

        mockMvc.perform(post("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGameById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputHalo);

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetGamesByStudio() throws Exception {
        String outputJson = mapper.writeValueAsString(bungieGames);

        mockMvc.perform(get("/games/studio/Bungie"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }
    @Test
    public void shouldGetGamesByEsrbRating() throws Exception {
        String outputJson = mapper.writeValueAsString(mRatedGames);

        mockMvc.perform(get("/games/esrb/M"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }
    @Test
    public void shouldGetGamesByTitle() throws Exception {
        String outputJson = mapper.writeValueAsString(haloGame);

        mockMvc.perform(get("/games/title/Halo"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllGames() throws Exception {
        String outputJson = mapper.writeValueAsString(allGames);

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingGame() throws Exception {
        inputHalo.setId(1);
        inputHalo.setQuantity(8);

        String inputJson = mapper.writeValueAsString(inputHalo);

        mockMvc.perform(put("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingGame() throws Exception {
        mockMvc.perform(delete("/games/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn422StatusCodeIfRequestIsInvalid() throws Exception {

        mockMvc.perform(get("/games/test"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());



        mockMvc.perform(get("/games/four"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


    }
}