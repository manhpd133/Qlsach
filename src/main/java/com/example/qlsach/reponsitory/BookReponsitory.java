package com.example.qlsach.reponsitory;

import com.example.qlsach.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReponsitory extends JpaRepository<Book, Long> {
    List<Book> findByNamebookContaining(String namebook);
}
