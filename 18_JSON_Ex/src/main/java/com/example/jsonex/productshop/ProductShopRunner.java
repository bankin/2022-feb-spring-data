package com.example.jsonex.productshop;

import com.example.jsonex.productshop.entities.categories.CategoryStats;
import com.example.jsonex.productshop.entities.products.ProductWithoutBuyerDTO;
import com.example.jsonex.productshop.entities.users.UserWithSoldProductsDTO;
import com.example.jsonex.productshop.services.ProductsService;
import com.example.jsonex.productshop.services.SeedService;
import com.example.jsonex.productshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductsService productsService;
    private final UserService userService;

    private final Gson gson;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();

//        productsBetweenPriceWithoutBuyer();

//        getUsersWithSoldProducts();

//        getCategoryStats();

        this.userService.getUsersWithSoldProductsOrderByCount();
    }

    private void getCategoryStats() {
        List<CategoryStats> categoryStatistics = this.productsService.getCategoryStatistics();

        String json = this.gson.toJson(categoryStatistics);

        System.out.println(json);
    }

    private void getUsersWithSoldProducts() {
        List<UserWithSoldProductsDTO> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();

        String json = this.gson.toJson(usersWithSoldProducts);

        System.out.println(json);
    }

    private void productsBetweenPriceWithoutBuyer() {
        List<ProductWithoutBuyerDTO> productsForSell = this.productsService.getProductsInPriceRangeForSell(500, 1000);

        String json = this.gson.toJson(productsForSell);

        System.out.println(json);
    }
}
