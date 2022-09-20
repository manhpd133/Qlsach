package com.example.qlsach.controller;

import com.example.qlsach.model.Book;
import com.example.qlsach.reponsitory.BookReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookReponsitory bookReponsitory;

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String namebook) {
        try {
            List<Book> books = new ArrayList<>();
            if (namebook == null) {
                bookReponsitory.findAll().forEach(books::add);
            } else
                bookReponsitory.findByNameBookContaining(namebook).forEach(books::add);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable("id") long id) {
        Optional<Book> bookData = bookReponsitory.findById(id);
        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            Book _book = bookReponsitory.save(new Book( book.getNameBook(), book.getBookShelves(), book.getAuthor(), book.getIdBookStore(), book.getReleaseDate(), book.getSaleDate()));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Optional<Book> bookData = bookReponsitory.findById(id);
        if (bookData.isPresent()) {
            Book bookEdit = bookData.get();
            bookEdit.setNameBook(book.getNameBook());
            bookEdit.setBookShelves(book.getBookShelves());
            bookEdit.setAuthor(book.getAuthor());
            bookEdit.setIdBookStore(book.getIdBookStore());
            bookEdit.setReleaseDate(book.getReleaseDate());
            bookEdit.setSaleDate(bookEdit.getSaleDate());
            return new ResponseEntity<>(bookReponsitory.save(bookEdit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Book> deleteBook (@PathVariable("id") long id) {
        try {
            bookReponsitory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/book")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookReponsitory.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
