package com.example.qlsach.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book_store")
public class BookStore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id_bookStore")
    private Long idBookStore;

    @Column(name = "name_bookstore")
    private String nameBookStore;

    @Column(name = "address")
    private String address;

//    public BookStore() {
//    }

//    public BookStore(String nameBookStore, String address) {
//        this.nameBookStore = nameBookStore;
//        this.address = address;
//    }
//
//    public long getIdBookStore() {
//        return idBookStore;
//    }
//
//    public void setIdBookStore(Long idbookstore) {
//        this.idBookStore = idbookstore;
//    }
//
//    public String getNameBookStore() {
//        return nameBookStore;
//    }
//
//    public void setNameBookStore(String namebookstore) {
//        this.nameBookStore = namebookstore;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

    @Override
    public String toString() {
        return "BookStore{" +
                "idBookStore=" + idBookStore +
                ", nameBookStore='" + nameBookStore + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
