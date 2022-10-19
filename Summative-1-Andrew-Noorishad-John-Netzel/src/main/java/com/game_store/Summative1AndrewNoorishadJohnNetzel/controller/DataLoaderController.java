package com.game_store.Summative1AndrewNoorishadJohnNetzel.controller;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.ProcessingFee;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.SalesTaxRate;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.ProcessingFeeRepository;
import com.game_store.Summative1AndrewNoorishadJohnNetzel.repository.TaxRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

public class DataLoaderController {
    @Autowired
    private TaxRateRepository taxRateRepo;
    @Autowired
    private ProcessingFeeRepository processingFeeRepository;

    @GetMapping("/load-data")
    public void loadData() {
        SalesTaxRate stateTax = new SalesTaxRate();
        stateTax.setState("AL");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("AK");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("AZ");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("AR");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("CA");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("CO");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("CT");
        stateTax.setRate(BigDecimal.valueOf(0.03));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("DE");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("FL");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("GA");
        stateTax.setRate(BigDecimal.valueOf(0.07));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("HI");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("ID");
        stateTax.setRate(BigDecimal.valueOf(0.03));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("IL");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("IN");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("IA");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("KS");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("KY");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("LA");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("ME");
        stateTax.setRate(BigDecimal.valueOf(0.03));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("MD");
        stateTax.setRate(BigDecimal.valueOf(0.07));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("MA");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("MI");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("MN");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("MS");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("MO");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("MT");
        stateTax.setRate(BigDecimal.valueOf(0.03));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("NE");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("NV");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("NH");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("NJ");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("NM");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("NY");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("NC");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("ND");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("OH");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("OK");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("OR");
        stateTax.setRate(BigDecimal.valueOf(0.07));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("PA");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("RI");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("SC");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("SD");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("TN");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("TX");
        stateTax.setRate(BigDecimal.valueOf(0.03));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("UT");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("VT");
        stateTax.setRate(BigDecimal.valueOf(0.07));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("VA");
        stateTax.setRate(BigDecimal.valueOf(0.06));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("WA");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("WV");
        stateTax.setRate(BigDecimal.valueOf(0.05));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("WI");
        stateTax.setRate(BigDecimal.valueOf(0.03));
        taxRateRepo.save(stateTax);

        stateTax = new SalesTaxRate();
        stateTax.setState("WY");
        stateTax.setRate(BigDecimal.valueOf(0.04));
        taxRateRepo.save(stateTax);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Consoles");
        processingFee.setFee(BigDecimal.valueOf(14.99));
        processingFeeRepository.save(processingFee);

        processingFee = new ProcessingFee();
        processingFee.setProductType("T-shirts");
        processingFee.setFee(BigDecimal.valueOf(1.98));
        processingFeeRepository.save(processingFee);

        processingFee = new ProcessingFee();
        processingFee.setProductType("Games");
        processingFee.setFee(BigDecimal.valueOf(1.49));
        processingFeeRepository.save(processingFee);
    }
}
