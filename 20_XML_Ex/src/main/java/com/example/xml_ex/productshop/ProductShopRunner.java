package com.example.xml_ex.productshop;

import com.example.xml_ex.productshop.entities.products.ExportProductsInRangeDto;
import com.example.xml_ex.productshop.entities.users.ExportSellersDTO;
import com.example.xml_ex.productshop.entities.users.ExportSellersWithCountsDTO;
import com.example.xml_ex.productshop.services.ProductsService;
import com.example.xml_ex.productshop.services.SeedService;
import com.example.xml_ex.productshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductsService productsService;
    private final UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedProducts();

//        this.productsInRange();

        this.findUserWithSoldProductsAndCounts();

    }

    private void findUserWithSoldProductsAndCounts() {
        ExportSellersWithCountsDTO dto = this.userService.findAllWithSoldProductsAndCounts();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        String result = gson.toJson(dto);

        System.out.println(result);
    }

    private void findUserWithSoldProducts() throws JAXBException {
        ExportSellersDTO result = this.userService.findAllWithSoldProducts();

        JAXBContext context = JAXBContext.newInstance(ExportSellersDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(result, System.out);
    }

    private void productsInRange() throws JAXBException {
        ExportProductsInRangeDto inRange = this.productsService.getInRange(500, 1000);

        JAXBContext context = JAXBContext.newInstance(ExportProductsInRangeDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(inRange, System.out);
    }
}
