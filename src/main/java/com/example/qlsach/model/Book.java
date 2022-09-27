package com.example.qlsach.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id_book")
    private long idBook;

    @Column(name = "name_book")
    private String bookName;

    @Column(name = "bookshelves")
    private Float bookShelves;

    @Column(name = "author")
    private String author;

    @Column(name = "id_bookstore")
    private int idBookStore;

    @Column(name = "release_date")
    private int releaseDate;

    @Column(name = "sale_date")
    private int saleDate;

    public Book() {
    }

    public Book(String bookName, Float bookShelves, String author, int idBookStore, int releaseDate, int saleDate) { // Tên biến có vấn đề
        this.bookName = bookName;
        this.bookShelves = bookShelves;
        this.author = author;
        this.idBookStore = idBookStore;
        this.releaseDate = releaseDate;
        this.saleDate = saleDate;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String nameBook) {
        this.bookName = nameBook;
    }

    public Float getBookShelves() {
        return bookShelves;
    }

    public void setBookShelves(Float bookShelves) {
        this.bookShelves = bookShelves;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIdBookStore() {
        return idBookStore;
    }

    public void setIdBookStore(int idBookStore) {
        this.idBookStore = idBookStore;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(int saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + idBook +
                ", nameBook='" + bookName + '\'' +
                ", bookShelves=" + bookShelves +
                ", author='" + author + '\'' +
                ", idBookStore=" + idBookStore +
                ", releaseDate=" + releaseDate +
                ", saleDate=" + saleDate +
                '}';
    }
}
