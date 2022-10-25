package com.example.qlsach.controller;

import com.example.qlsach.service.BookStoreServices;
import com.example.qlsach.model.BookStore;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/book_store")
public class BookStoreController {

    @Autowired
    private BookStoreServices bookStoreServices;

    @GetMapping("/init-data")
    private ResponseEntity<?> initDataBook() throws GeneralSecurityException, IOException {
        bookStoreServices.syncDataFromSheet();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ()
    public ResponseEntity<List<BookStore>> getAllBookStores(@RequestParam (required = false, value = "bookStore_name") String bookStoreName) {
        return bookStoreServices.getAllBookStore(bookStoreName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookStore> getBookStoreByID(@PathVariable("id") long id) {
        return bookStoreServices.getBookStoreByID(id);
    }

    @PostMapping()
    public ResponseEntity<BookStore> createBookStore(@RequestBody BookStore bookStore) {
       return bookStoreServices.createBookStore(bookStore);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BookStore> EditBookStore(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException, JsonProcessingException {
        return bookStoreServices.handleEditBookStore(id);
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
