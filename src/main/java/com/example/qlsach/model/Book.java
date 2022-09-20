package com.example.qlsach.model;

import javax.persistence.*;

@Entity
@Table(name = "Book") // Tên table viết thường
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBook;        // Thêm @Column sửa tên cột trong DB

    @Column(name = "namebook")  // Tên column nên đặt là name_book
    private String nameBook;

    @Column(name = "bookshelves")
    private Float bookShelves;

    @Column(name = "author")
    private String author;

    @Column(name = "idbookstore")
    private int idBookStore;

    @Column(name = "releasedate")
    private int releaseDate;

    @Column(name = "saledate")
    private int saleDate;

    public Book() {
    }

    public Book(String namebook, Float bookShelves, String author, int idBookStore, int releasedate, int saledate) { // Tên biến có vấn đề
        this.nameBook = namebook;
        this.bookShelves = bookShelves;
        this.author = author;
        this.idBookStore = idBookStore;
        this.releaseDate = releasedate;
        this.saleDate = saledate;
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

    public void setIdBookStore(int idbookstore) {
        this.idBookStore = idbookstore;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releasedate) {
        this.releaseDate = releasedate;
    }

    public int getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(int saledate) {
        this.saleDate = saledate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + idBook +
                ", namebook='" + nameBook + '\'' +
                ", bookshelves=" + bookShelves +
                ", author='" + author + '\'' +
                ", idbookstore=" + idBookStore +
                ", releasedate=" + releaseDate +
                ", saledate=" + saleDate +
                '}';
    }
}
