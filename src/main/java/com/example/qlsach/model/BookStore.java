package com.example.qlsach.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "book_store")
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id_bookStore")
    private long idBookStore;

    @Column(name = "name_bookstore")
    private String bookNameStore;

    @Column(name = "address")
    private String address;

    public BookStore() {
    }

    public BookStore(String bookNameStore, String address) {
        this.bookNameStore = bookNameStore;
        this.address = address;
    }

    public long getIdBookStore() {
        return idBookStore;
    }

    public void setIdBookStore(int idbookstore) {
        this.idBookStore = idbookstore;
    }

    public String getBookNameStore() {
        return bookNameStore;
    }

    public void setBookNameStore(String namebookstore) {
        this.bookNameStore = namebookstore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BookStore{" +
                "idBookStore=" + idBookStore +
                ", nameBookStore='" + bookNameStore + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
