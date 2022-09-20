package com.example.qlsach.controller;

import com.example.qlsach.model.BookStore;
import com.example.qlsach.reponsitory.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/book_store")
public class BookStoreController {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @GetMapping ()
    public ResponseEntity<List<BookStore>> getAllBookStores(@RequestParam (required = false) String nameBookStore) {
        try {
            List<BookStore> bookStores = new ArrayList<>();
            if (nameBookStore == null) {
                bookStoreRepository.findAll().forEach(bookStores::add);
            } else {
                bookStoreRepository.findByNameBookStore(nameBookStore).forEach(bookStores::add);
            }
            if (bookStores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bookStores, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookStore> getBookStoreByID(@PathVariable("id") long id) {
        Optional <BookStore> bookStoreData = bookStoreRepository.findById(id);
        if (bookStoreData.isPresent()) {
            return new ResponseEntity<>(bookStoreData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<BookStore> createBookStore(@RequestBody BookStore bookStore) {
        try {
            BookStore bookStoreAdd = bookStoreRepository.save(new BookStore(bookStore.getNameBookStore(), bookStore.getAddress()));
            return new ResponseEntity<>(bookStoreAdd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookStore> updateBookStore(@PathVariable("id") long id, @RequestBody BookStore bookStore) {
        Optional<BookStore> bookStoreData = bookStoreRepository.findById(id);
        if (bookStoreData.isPresent()) {
            BookStore bookStoreEdit = bookStoreData.get();
            bookStoreEdit.setNameBookStore(bookStore.getNameBookStore());
            bookStoreEdit.setAddress(bookStore.getAddress());
            return new ResponseEntity<>(bookStoreRepository.save(bookStoreEdit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookStore> deleteBookStore (@PathVariable("id") long id) {
        try {
            bookStoreRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllBookStore() {
        try {
            bookStoreRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
