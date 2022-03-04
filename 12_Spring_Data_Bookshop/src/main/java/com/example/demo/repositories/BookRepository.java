package com.example.demo.repositories;

import com.example.demo.entities.Book;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository
        extends JpaRepository<Book, Integer> {

    List<Book> findByReleaseDateAfter(LocalDate releaseDate);

    int countByReleaseDateAfter(LocalDate releaseDate);
}
