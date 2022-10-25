package com.example.qlsach.reponsitory;

import com.example.qlsach.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
    List<BookStore> findByNameBookStore(String nameBookStore);
//    List<BookStore> findById(Long id);
}
