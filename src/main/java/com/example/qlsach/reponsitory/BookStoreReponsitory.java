package com.example.qlsach.reponsitory;

import com.example.qlsach.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStoreReponsitory  extends JpaRepository<BookStore, Long> {
    List<BookStore> findByNameBookStoreContaining(String namebookstore);
}

