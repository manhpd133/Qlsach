package com.example.qlsach.Service;

import com.example.qlsach.model.Book;
import com.example.qlsach.reponsitory.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServices {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<List<Book>> getAllBooks(String nameBook) {
        try {
            List<Book> books = new ArrayList<>();
            if (nameBook == null) {
                bookRepository.findAll().forEach(books::add);
            } else
                bookRepository.findByNameBook(nameBook).forEach(books::add);
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> getBookByID(long id) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Book> createBook(Book book) {
        try {
            Book bookAdd = bookRepository.save(new Book( book.getBookName(), book.getBookShelves(), book.getAuthor(), book.getIdBookStore(), book.getReleaseDate(), book.getSaleDate()));
            return new ResponseEntity<>(bookAdd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> updateBook(long id,Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book bookEdit = bookData.get();
            bookEdit.setBookName(book.getBookName());
            bookEdit.setBookShelves(book.getBookShelves());
            bookEdit.setAuthor(book.getAuthor());
            bookEdit.setIdBookStore(book.getIdBookStore());
            bookEdit.setReleaseDate(book.getReleaseDate());
            bookEdit.setSaleDate(bookEdit.getSaleDate());
            return new ResponseEntity<>(bookRepository.save(bookEdit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Book> deleteBook(long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
