package com.example.book_store_management_system.service;

import com.example.book_store_management_system.model.Book;
import com.example.book_store_management_system.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final IBookRepository theBookRepository;

    public BookService(IBookRepository bookRepository) {
        this.theBookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return theBookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return theBookRepository.findById(id).orElse(null);
    }

    public Book getBookByTitle(String title) {
        return theBookRepository.findByTitle(title).orElse(null);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> optionalBook = theBookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublisher(bookDetails.getPublisher());
            book.setIsbn(bookDetails.getIsbn());
            book.setPrice(bookDetails.getPrice());
            return theBookRepository.save(book);
        }
        return null;
    }
    public void saveBook(Book book) {
        theBookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        theBookRepository.deleteById(id);
    }
}

