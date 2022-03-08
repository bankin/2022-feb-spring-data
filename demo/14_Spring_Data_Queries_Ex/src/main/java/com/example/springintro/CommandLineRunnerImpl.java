package com.example.springintro;

import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();

        Scanner scanner = new Scanner(System.in);

        int amount = Integer.parseInt(scanner.nextLine());
        int deleteCount = this.bookService.deleteWithCopiesLessThan(amount);
        System.out.println(deleteCount + " books were deleted.");

//        12
//        String date = scanner.nextLine();
//        int amount = Integer.parseInt(scanner.nextLine());
//        int booksUpdated = this.bookService.addCopiesToBooksAfter(date, amount);
//        System.out.printf("%d books are released after %s, " +
//                "so total of %d book copies were added",
//                booksUpdated, date, amount * booksUpdated);

//        11
//        String title = scanner.nextLine();
//        BookSummary summary = this.bookService.getInformationForTitle(title);
//        System.out.println(summary.getTitle() + " " + summary.getEditionType() +
//                " " + summary.getAgeRestriction() + " " + summary.getPrice());

//        10
//        this.authorService.getWithTotalCopies()
//            .forEach(a -> System.out.println(
//                    a.getFirstName() + " " + a.getLastName() +
//                    " - " + a.getTotalCopies()));

//        09
//        int length = Integer.parseInt(scanner.nextLine());
//        int totalBooks = this.bookService.countBooksWithTitleLongerThan(length);
//        System.out.printf("There are %d books with longer title than %d symbols%n",
//                totalBooks, length);

//        08
//        String search = scanner.nextLine();
//        this.bookService.findByAuthorLastNameStartsWith(search)
//            .forEach(b -> System.out.printf("%s (%s %s)%n",
//                b.getTitle(), b.getAuthor().getFirstName(),
//                b.getAuthor().getLastName()));

//        07
//        String search = scanner.nextLine();
//        this.bookService.findAllTitlesContaining(search)
//                .forEach(System.out::println);

//        06
//        String endsWith = scanner.nextLine();
//        this.authorService.findByFirstNameEndingWith(endsWith)
//            .stream()
//            .map(a -> a.getFirstName() + " " + a.getLastName())
//            .forEach(System.out::println);


//        05
//        String date = scanner.nextLine();
//        this.bookService.findBooksReleasedBefore(date)
//            .forEach(b -> System.out.printf("%s %s %.2f%n",
//                b.getTitle(), b.getEditionType(), b.getPrice()));

//        04
//        int releaseYear = Integer.parseInt(scanner.nextLine());
//        this.bookService.findNotReleasedIn(releaseYear)
//                .forEach(b -> System.out.println(b.getTitle()));

//        03
//        this.bookService.findAllWithPriceNotBetween(5, 40)
//            .forEach(b -> System.out.println(b.getTitle() + " - " + b.getPrice()));

//        02
//        this.bookService.findAllTitlesByEditionAndCopies(EditionType.GOLD, 5000)
//                .forEach(System.out::println);


//        01
//        this.bookService.findAllTitlesByAgeRestriction(restriction)
//            .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
