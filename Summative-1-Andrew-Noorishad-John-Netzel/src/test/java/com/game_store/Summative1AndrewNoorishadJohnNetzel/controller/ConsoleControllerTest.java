package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Console;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.ConsoleRepository;
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
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConsoleRepository consoleRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Console inputSeriesX;
    Console outputSeriesX;
    Console outputSeriesS;
    Console outputPlayStation5;

    List<Console> microsoftConsoles;
    List<Console> allConsoles;

    @Before
    public void setUp() throws Exception {
        inputSeriesX = new Console("Xbox Series X","Microsoft","32GB","5ghz", BigDecimal.valueOf(399.99),10);
        outputSeriesX = new Console("Xbox Series X","Microsoft","32GB","5ghz",BigDecimal.valueOf(399.99),10);
        outputSeriesX.setId(1);
        outputSeriesS = new Console("Xbox Series S","Microsoft","16GB","4ghz",BigDecimal.valueOf(299.99),10);
        outputSeriesS.setId(2);
        outputPlayStation5 = new Console("PlayStation 5","Sony","32GB","5ghz",BigDecimal.valueOf(399.99),20);
        outputPlayStation5.setId(3);

        microsoftConsoles = new ArrayList<>(Arrays.asList(
                outputSeriesX,
                outputSeriesS
        ));

        allConsoles = new ArrayList<>(Arrays.asList(
                outputSeriesX,
                outputSeriesS,
                outputPlayStation5
        ));

        doReturn(outputSeriesX).when(consoleRepository).save(inputSeriesX);
        doReturn(Optional.of(outputSeriesX)).when(consoleRepository).findById(1);
        doReturn(allConsoles).when(consoleRepository).findAll();
        doReturn(microsoftConsoles).when(consoleRepository).findByManufacturer("Microsoft");
    }

    @Test
    public void shouldAddConsoleOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputSeriesX);
        String outputJson = mapper.writeValueAsString(outputSeriesX);

        mockMvc.perform(post("/consoles")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetConsoleById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputSeriesX);

        mockMvc.perform(get("/consoles/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetConsolesByManufacturer() throws Exception {
        String outputJson = mapper.writeValueAsString(microsoftConsoles);

        mockMvc.perform(get("/consoles/manufacturer/Microsoft"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllConsoles() throws Exception {
        String outputJson = mapper.writeValueAsString(allConsoles);

        mockMvc.perform(get("/consoles"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingConsole() throws Exception {
        inputSeriesX.setId(1);
        inputSeriesX.setQuantity(8);

        String inputJson = mapper.writeValueAsString(inputSeriesX);

        mockMvc.perform(put("/consoles")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingConsole() throws Exception {
        mockMvc.perform(delete("/consoles/1"))
                .andExpect(status().isNoContent());
    }

}