package com.example.advquerying;

import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {

    private final ShampooRepository shampooRepository;

    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    @Autowired
    public Runner(
            ShampooRepository shampooRepository,
            ShampooService shampooService,
            IngredientService ingredientService) {
        this.shampooRepository = shampooRepository;
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    public void run(String... args) {
        this.ingredientService.increasePriceByPercentage(0.1);
//            .forEach(System.out::println);
    }

    private void demo() {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        String second = scanner.nextLine();

        Set<String> names = Set.of(first, second);

        this.shampooRepository.findByIngredientsNames(names)
                .forEach(System.out::println);


//        String sizeName = scanner.nextLine();
//        Size size = Size.valueOf(sizeName);
//
//        this.shampooRepository.findBySizeOrderById(size)
//            .forEach(System.out::println);
    }
}
