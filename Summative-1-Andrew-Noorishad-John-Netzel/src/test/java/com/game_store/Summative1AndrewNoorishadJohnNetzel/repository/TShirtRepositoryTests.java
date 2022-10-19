package com.game_store.Summative1AndrewNoorishadJohnNetzel.repository;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.TShirt;
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
public class TShirtRepositoryTests {

    @Autowired
    TShirtRepository tShirtRepository;

    @Before
    public void setUp() throws Exception {
        tShirtRepository.deleteAll();

        tShirtRepository.save(new TShirt("Large","Black","Large Black T-Shirt", BigDecimal.valueOf(9.99),10));
        tShirtRepository.save(new TShirt("Large","Blue","Large Blue T-Shirt", BigDecimal.valueOf(9.99),10));
        tShirtRepository.save(new TShirt("Small","Black","Small Black T-Shirt", BigDecimal.valueOf(9.99),10));
    }

    @Test
    public void shouldFindTShirtsByColor() {
        List<TShirt> blackTShirts = tShirtRepository.findByColor("Black");

        assertEquals(2,blackTShirts.size());
    }

    @Test
    public void shouldFindTShirtsBySize() {
        List<TShirt> largeTShirts = tShirtRepository.findBySize("Large");

        assertEquals(2,largeTShirts.size());
    }
}