package com.example.qlsach.model;

import javax.persistence.*;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "namebook")
    private String namebook;

    @Column(name = "bookshelves")
    private Float bookshelves;

    @Column(name = "author")
    private String author;

    @Column(name = "idbookstore")
    private int idbookstore;

    @Column(name = "releasedate")
    private int releasedate;

    @Column(name = "saledate")
    private int saledate;

    public Book() {
    }

    public Book(String namebook, Float bookshelves, String author, int idbookstore, int releasedate, int saledate) {
        this.namebook = namebook;
        this.bookshelves = bookshelves;
        this.author = author;
        this.idbookstore = idbookstore;
        this.releasedate = releasedate;
        this.saledate = saledate;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamebook() {
        return namebook;
    }

    public void setNamebook(String namebook) {
        this.namebook = namebook;
    }

    public Float getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Float bookshelves) {
        this.bookshelves = bookshelves;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIdbookstore() {
        return idbookstore;
    }

    public void setIdbookstore(int idbookstore) {
        this.idbookstore = idbookstore;
    }

    public int getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(int releasedate) {
        this.releasedate = releasedate;
    }

    public int getSaledate() {
        return saledate;
    }

    public void setSaledate(int saledate) {
        this.saledate = saledate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", namebook='" + namebook + '\'' +
                ", bookshelves=" + bookshelves +
                ", author='" + author + '\'' +
                ", idbookstore=" + idbookstore +
                ", releasedate=" + releasedate +
                ", saledate=" + saledate +
                '}';
    }
}
