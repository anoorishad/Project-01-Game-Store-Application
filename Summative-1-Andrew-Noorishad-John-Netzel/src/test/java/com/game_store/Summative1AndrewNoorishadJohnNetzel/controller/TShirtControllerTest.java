package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.TShirt;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.TShirtRepository;
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
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TShirtRepository tShirtRepository;

    private ObjectMapper mapper = new ObjectMapper();

    TShirt inputLargeBlack;
    TShirt outputLargeBlack;
    TShirt outputLargeBlue;
    TShirt outputSmallBlack;

    List<TShirt> largeTShirts;
    List<TShirt> blackTShirts;
    List<TShirt> allTShirts;

    @Before
    public void setUp() throws Exception {
        inputLargeBlack = new TShirt("Large","Black","Large Black T-Shirt", BigDecimal.valueOf(9.99),10);
        outputLargeBlack = new TShirt("Large","Black","Large Black T-Shirt", BigDecimal.valueOf(9.99),10);
        outputLargeBlack.setId(1);
        outputLargeBlue = new TShirt("Large","Blue","Large Blue T-Shirt", BigDecimal.valueOf(9.99),10);
        outputLargeBlue.setId(2);
        outputSmallBlack = new TShirt("Small","Black","Small Black T-Shirt", BigDecimal.valueOf(9.99),10);
        outputSmallBlack.setId(3);

        largeTShirts = new ArrayList<>(Arrays.asList(
                outputLargeBlack,
                outputLargeBlue
        ));

        blackTShirts = new ArrayList<>(Arrays.asList(
                outputLargeBlack,
                outputSmallBlack
        ));

        allTShirts = new ArrayList<>(Arrays.asList(
                outputLargeBlack,
                outputLargeBlue,
                outputSmallBlack
        ));

        doReturn(outputLargeBlack).when(tShirtRepository).save(inputLargeBlack);
        doReturn(Optional.of(outputLargeBlack)).when(tShirtRepository).findById(1);
        doReturn(allTShirts).when(tShirtRepository).findAll();
        doReturn(largeTShirts).when(tShirtRepository).findBySize("Large");
        doReturn(blackTShirts).when(tShirtRepository).findByColor("Black");

    }

    @Test
    public void shouldAddTShirtOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputLargeBlack);
        String outputJson = mapper.writeValueAsString(outputLargeBlack);

        mockMvc.perform(post("/tshirts")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTShirtById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputLargeBlack);

        mockMvc.perform(get("/tshirts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetTShirtsBySize() throws Exception {
        String outputJson = mapper.writeValueAsString(largeTShirts);

        mockMvc.perform(get("/tshirts/size/Large"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }
    @Test
    public void shouldGetTShirtsByColor() throws Exception {
        String outputJson = mapper.writeValueAsString(blackTShirts);

        mockMvc.perform(get("/tshirts/color/Black"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllTShirts() throws Exception {
        String outputJson = mapper.writeValueAsString(allTShirts);

        mockMvc.perform(get("/tshirts"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingTShirt() throws Exception {
        inputLargeBlack.setId(1);
        inputLargeBlack.setQuantity(8);

        String inputJson = mapper.writeValueAsString(inputLargeBlack);

        mockMvc.perform(put("/tshirts")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingTShirt() throws Exception {
        mockMvc.perform(delete("/tshirts/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn422StatusCodeIfRequestIsInvalid() throws Exception {

        mockMvc.perform(get("/tshirts/test"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());



        mockMvc.perform(get("/tshirts/four"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


    }
}