package com.example.qlsach.Service;

import com.example.qlsach.model.BookStore;
import com.example.qlsach.reponsitory.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServices {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    public ResponseEntity<List<BookStore>> getAllBookStore(String nameBookStore) {
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

    public ResponseEntity<BookStore> getBookStoreByID(long id){
        Optional<BookStore> bookStoreData = bookStoreRepository.findById(id);
        if (bookStoreData.isPresent()) {
            return new ResponseEntity<>(bookStoreData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<BookStore> createBookStore (BookStore bookStore) {
        try {
            BookStore bookStoreAdd = bookStoreRepository.save(new BookStore(bookStore.getNameBookStore(), bookStore.getAddress()));
            return new ResponseEntity<>(bookStoreAdd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BookStore> updateBookStore(long id, BookStore bookStore) {
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

    public ResponseEntity<BookStore> deleteBookStoreId(long id) {
        try {
            bookStoreRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllBookStore() {
        try {
            bookStoreRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
