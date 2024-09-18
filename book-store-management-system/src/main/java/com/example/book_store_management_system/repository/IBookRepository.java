package com.example.book_store_management_system.repository;

import com.example.book_store_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    Optional<Book> findById(Long id);

    void deleteById(Long id);
}
