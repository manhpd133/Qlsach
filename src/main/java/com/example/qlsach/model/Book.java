package com.example.qlsach.model;

import javax.persistence.*;

@Entity
@Table(name = "book") // Tên table viết thường
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private long idBook;        // Thêm @Column sửa tên cột trong DB

    @Column(name = "name_book")  // Tên column nên đặt là name_book
    private String nameBook;

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

    public Book(String nameBook, Float bookShelves, String author, int idBookStore, int releaseDate, int saleDate) { // Tên biến có vấn đề
        this.nameBook = nameBook;
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

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
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
                ", nameBook='" + nameBook + '\'' +
                ", bookShelves=" + bookShelves +
                ", author='" + author + '\'' +
                ", idBookStore=" + idBookStore +
                ", releaseDate=" + releaseDate +
                ", saleDate=" + saleDate +
                '}';
    }
}
