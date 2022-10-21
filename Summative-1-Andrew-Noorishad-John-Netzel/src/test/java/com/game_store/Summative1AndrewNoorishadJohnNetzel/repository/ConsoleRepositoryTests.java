package com.game_store.Summative1AndrewNoorishadJohnNetzel.repository;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleRepositoryTests {

    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();

        consoleRepository.save(new Console("Xbox Series X","Microsoft","32GB","5ghz",BigDecimal.valueOf(199.99),10));

        consoleRepository.save(new Console("PlayStation 5","Sony","32GB","5ghz",BigDecimal.valueOf(299.99),20));

        consoleRepository.save(new Console("Xbox Series S","Microsoft","8GB","2ghz",BigDecimal.valueOf(199.99),25));
    }

    @Test
    public void shouldFindConsolesByManufacturer() {
        List<Console> microsoftConsoles = consoleRepository.findByManufacturer("Microsoft");

        assertEquals(2, microsoftConsoles.size());
    }

}