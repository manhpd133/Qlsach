package com.example.qlsach.service;

import com.example.qlsach.googleapi.SheetsServiceUtil;
import com.example.qlsach.model.BookStore;
import com.example.qlsach.reponsitory.BookStoreRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BookStoreServices {
    @Autowired
    private SheetsServiceUtil sheetsServiceUtil;

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    public void syncDataFromSheet () throws GeneralSecurityException, IOException {
        log.info("Syncing data book from google sheet");
        sheetsServiceUtil.renderVocabFromGoogleSheetToDatabase();
    }

    public ResponseEntity<List<BookStore>> getAllBookStore(String bookNameStore) {
        try {
            List<BookStore> bookStores = new ArrayList<>();
            if (bookNameStore == null) {
                bookStoreRepository.findAll().forEach(bookStores::add);
            } else {
                bookStoreRepository.findByNameBookStore(bookNameStore).forEach(bookStores::add);
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
            BookStore bookStoreAdd = bookStoreRepository.save(new BookStore(bookStore.getIdBookStore(),bookStore.getNameBookStore(), bookStore.getAddress()));
            return new ResponseEntity<>(bookStoreAdd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Cacheable(value = "BookStore", key = "#nameBookStore")
    public List<BookStore> findByNameBookStores (String nameBookStore) {
        return bookStoreRepository.findByNameBookStore(nameBookStore);
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

    public BookStore findBookStoreById(Long idBookStore) throws ChangeSetPersister.NotFoundException {
        BookStore bookStore = null;
        String cacheKey = "bookStore" + idBookStore;
        bookStore = (BookStore) redisTemplate.opsForValue().get(cacheKey);
        if (bookStore == null) {
            Optional<BookStore> o_bookStore = bookStoreRepository.findById(idBookStore);
            if (o_bookStore.isEmpty()) {
                throw new ChangeSetPersister.NotFoundException();
            } else {
                bookStore = o_bookStore.get();
                redisTemplate.opsForValue().set(cacheKey, bookStore, 60, TimeUnit.SECONDS);
            }
        }
        return bookStore;
    }

    public ResponseEntity<BookStore> handleEditBookStore (long id) throws ChangeSetPersister.NotFoundException, JsonProcessingException {
        BookStore bookStore = findBookStoreById(id);
//        bookStore.setNameBookStore(bookStore.getNameBookStore());
//        bookStore.setAddress(bookStore.getAddress());
//
//        BookStore editBookStore = bookStoreRepository.save(bookStore);
        String cacheKey = "bookStore" + id;
        ObjectMapper mapper = new ObjectMapper();
        redisTemplate.opsForValue().set(cacheKey,mapper.writeValueAsString(bookStore));
        return null;
    }

}
