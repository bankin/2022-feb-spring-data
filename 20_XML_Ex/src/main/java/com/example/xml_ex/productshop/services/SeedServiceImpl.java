package com.example.xml_ex.productshop.services;

import com.example.xml_ex.productshop.entities.categories.Category;
import com.example.xml_ex.productshop.entities.products.Product;
import com.example.xml_ex.productshop.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path USERS_JSON_PATH =
            Path.of("src","main","resources","productshop","users.json");
    private static final Path CATEGORIES_JSON_PATH =
            Path.of("src","main","resources","productshop","categories.json");
    private static final Path PRODUCTS_JSON_PATH =
            Path.of("src","main","resources","productshop","products.json");

//    private final UserRepository userRepository;
//    private final CategoryRepository categoryRepository;
//    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(
//            UserRepository userRepository,
//                           CategoryRepository categoryRepository,
//                           ProductRepository productRepository
                           ) {
//        this.userRepository = userRepository;
//        this.categoryRepository = categoryRepository;
//        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {

    }

    @Override
    public void seedCategories() throws FileNotFoundException {

    }

    @Override
    public void seedProducts() throws FileNotFoundException {

    }

//    private Product setRandomCategories(Product product) {
//        Random random = new Random();
//        long categoriesDbCount = this.categoryRepository.count();
//
//        int count = random.nextInt((int) categoriesDbCount);
//
//        Set<Category> categories = new HashSet<>();
//        for (int i = 0; i < count; i++) {
//            int randomId = random.nextInt((int) categoriesDbCount) + 1;
//
//            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);
//
//            categories.add(randomCategory.get());
//        }
//
//        product.setCategories(categories);
//        return product;
//    }
//
//    private Product setRandomBuyer(Product product) {
//        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
//            return product;
//        }
//
//        Optional<User> buyer = getRandomUser();
//
//        product.setBuyer(buyer.get());
//
//        return product;
//    }
//
//    private Product setRandomSeller(Product product) {
//        Optional<User> seller = getRandomUser();
//
//        product.setSeller(seller.get());
//
//        return product;
//    }
//
//    private Optional<User> getRandomUser() {
////        long usersCount = this.userRepository.count(); // 1..5
////
////        // 0..4
////        int randomUserId = new Random().nextInt((int) usersCount) + 1;
////
////        return this.userRepository.findById(randomUserId);
//
//        return Optional.empty();
//    }
}
