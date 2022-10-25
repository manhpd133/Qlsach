package com.example.qlsach.controller;

import com.example.qlsach.model.Book;
import com.example.qlsach.service.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false , value = "book_name") String bookName) {
        return bookServices.getAllBooks(bookName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable("id") long id) {
        return bookServices.getBookByID(id);
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return bookServices.createBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        return bookServices.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook (@PathVariable("id") long id) {
        return bookServices.deleteBook(id);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        return bookServices.deleteAllBooks();
    }

}
