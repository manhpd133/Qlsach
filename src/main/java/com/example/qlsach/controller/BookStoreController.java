package com.example.qlsach.controller;

import com.example.qlsach.reponsitory.BookStoreReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.qlsach.model.BookStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BookStoreController {

    @Autowired
    BookStoreReponsitory bookStoreReponsitory;

    @GetMapping ("/bookstore")
    public ResponseEntity<List<BookStore>> getAllBookStores(@RequestParam (required = false) String namebookstore) {
        try {
            List<BookStore> bookStores = new ArrayList<>();

            if (namebookstore == null) {
                bookStoreReponsitory.findAll().forEach(bookStores::add);
            }
            else {
                bookStoreReponsitory.findByNamebookstoreContaining(namebookstore).forEach(bookStores::add);
            }
            if (bookStores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookStores, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookstore/{id}")
    public ResponseEntity<BookStore> getBookStoreByID(@PathVariable("id") long id) {
        Optional <BookStore> bookStoreData = bookStoreReponsitory.findById(id);

        if (bookStoreData.isPresent()) {
            return new ResponseEntity<>(bookStoreData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/bookstore")
    public ResponseEntity<BookStore> createBookStore(@RequestBody BookStore bookStore) {
        try {
            BookStore _bookstore = bookStoreReponsitory.save(new BookStore(bookStore.getNamebookstore(), bookStore.getAddress()));
            return new ResponseEntity<>(_bookstore, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bookstore/{id}")
    public ResponseEntity<BookStore> updateBookStore(@PathVariable("id") long id, @RequestBody BookStore bookStore) {
        Optional<BookStore> bookStoreData = bookStoreReponsitory.findById(id);

        if (bookStoreData.isPresent()) {
            BookStore _bookstore = bookStoreData.get();
            _bookstore.setNamebookstore(bookStore.getNamebookstore());
            _bookstore.setAddress(bookStore.getAddress());
            return new ResponseEntity<>(bookStoreReponsitory.save(_bookstore), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bookstore/{id}")
    public ResponseEntity<BookStore> deleteBookStore (@PathVariable("id") long id) {
        try {
            bookStoreReponsitory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bookstore")
    public ResponseEntity<HttpStatus> deleteAllBookStore() {
        try {
            bookStoreReponsitory.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
