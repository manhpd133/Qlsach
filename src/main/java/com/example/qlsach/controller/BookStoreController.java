package com.example.qlsach.controller;

import com.example.qlsach.Service.BookStoreServices;
import com.example.qlsach.model.BookStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/book_store")
public class BookStoreController {

    @Autowired
    private BookStoreServices bookStoreServices;

    @GetMapping ()
    public ResponseEntity<List<BookStore>> getAllBookStores(@RequestParam (required = false) String nameBookStore) {
        return bookStoreServices.getAllBookStore(nameBookStore);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookStore> getBookStoreByID(@PathVariable("id") long id) {
        return bookStoreServices.getBookStoreByID(id);
    }

    @PostMapping()
    public ResponseEntity<BookStore> createBookStore(@RequestBody BookStore bookStore) {
       return bookStoreServices.createBookStore(bookStore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookStore> updateBookStore(@PathVariable("id") long id, @RequestBody BookStore bookStore) {
        return bookStoreServices.updateBookStore(id, bookStore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookStore> deleteBookStoreId (@PathVariable("id") long id) {
       return bookStoreServices.deleteBookStoreId(id);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllBookStore() {
       return bookStoreServices.deleteAllBookStore();
    }
}
