package com.example.book_store_management_system.controller;

import com.example.book_store_management_system.model.Book;
import com.example.book_store_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "home";

    }

    @GetMapping("get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return book != null ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        Book book = bookService.getBookByTitle(title);
        return book != null ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // @PostMapping("/add")
    // public Book createBook(@RequestBody Book book) {
    //     return bookService.saveBook(book);
    // }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        model.addAttribute("username", username);
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam String title,
                           @RequestParam String author,
                           @RequestParam String publisher,
                           @RequestParam String isbn,
                           @RequestParam double price,
                           RedirectAttributes redirectAttributes) {
        Book book = new Book(title, author, publisher, isbn, price);
        bookService.saveBook(book);                    
                            
        redirectAttributes.addFlashAttribute("message", "Book added successfully!");
        return "redirect:/books/all";
    }



    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        model.addAttribute("username", username);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String publisher,
                             @RequestParam String isbn,
                             @RequestParam double price,
                             RedirectAttributes redirectAttributes) {
        Book bookDetails = new Book(title, author, publisher, isbn, price);
        Book updatedBook = bookService.updateBook(id, bookDetails);
        if (updatedBook != null) {
            redirectAttributes.addFlashAttribute("message", "Book updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to update book. Book not found.");
        }
        return "redirect:/books/all";
    }



    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books/all"; 
    }
    
    @GetMapping("/order-confirmation/{id}")
    public String orderConfirmation(@PathVariable Long id,Model model) {
    	Book book = bookService.getBookById(id);
    if (book != null) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        model.addAttribute("username", username);
        model.addAttribute("book", book);
        return "order-confirmation";
        } else {
            return "error"; 
        }
    }
}

