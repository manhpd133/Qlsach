package com.example.qlsach.model;

import javax.persistence.*;

@Entity
@Table(name = "book_store") // tên table nên đặt là book_store
public class BookStore {
    // Lỗi tương tự như Class Book
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bookstore")
    private long idBookStore;

    @Column(name = "name_bookstore")
    private String nameBookStore;

    @Column(name = "address")
    private String address;

    public BookStore() {
    }

    public BookStore(String nameBookStore, String address) {
        this.nameBookStore = nameBookStore;
        this.address = address;
    }

    public long getIdBookStore() {
        return idBookStore;
    }

    public void setIdBookStore(int idbookstore) {
        this.idBookStore = idbookstore;
    }

    public String getNameBookStore() {
        return nameBookStore;
    }

    public void setNameBookStore(String namebookstore) {
        this.nameBookStore = namebookstore;
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
                ", nameBookStore='" + nameBookStore + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
