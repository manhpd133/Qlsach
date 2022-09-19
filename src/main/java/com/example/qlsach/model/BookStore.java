package com.example.qlsach.model;

import javax.persistence.*;

@Entity
@Table(name = "bookstore")
public class BookStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idbookstore;

    @Column(name = "namebookstore")
    private String namebookstore;

    @Column(name = "address")
    private String address;

    public BookStore() {
    }

    public BookStore(String namebookstore, String address) {

        this.namebookstore = namebookstore;
        this.address = address;
    }

    public long getIdbookstore() {
        return idbookstore;
    }

    public void setIdbookstore(int idbookstore) {
        this.idbookstore = idbookstore;
    }

    public String getNamebookstore() {
        return namebookstore;
    }

    public void setNamebookstore(String namebookstore) {
        this.namebookstore = namebookstore;
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
                "idbookstore=" + idbookstore +
                ", namebookstore='" + namebookstore + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
