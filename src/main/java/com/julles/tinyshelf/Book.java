/*
 * This software is free to use
 * @author julles
 */

package com.julles.tinyshelf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Author(s)")
    private String author;

    @JsonProperty("Publisher")
    private String publisher;

    @JsonProperty("Publication Year")
    private int year;

    @JsonProperty("Pages")
    private int numPages;

    @JsonProperty("ISBN")
    private String isbn;

    @JsonProperty("Rating")
    private double rating;

    @JsonProperty("Other information")
    private String otherInfo;

    //@JsonFormat(pattern = "MM.dd.yyyy G 'at' HH:mm:ss")
    @JsonProperty("Created")
    private LocalDateTime dateCreated;

    //@JsonFormat(pattern = "MM.dd.yyyy G 'at' HH:mm:ss")
    @JsonProperty("Last Modified")
    private LocalDateTime dateModified;


    public Book(){

    };


    public Book(String title, String author, String publisher, int year, int numPages, String isbn, double rating, String otherInfo){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.numPages = numPages;
        this.isbn = isbn;
        this.rating = rating;
        this.otherInfo = otherInfo;
        this.dateCreated = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();  
    };
    

    /*  Getters */

    public String getTitle(){
        return this.title;
    }
    
    public String getAuthor(){
        return this.author;
    }
    
    public String getPublisher(){
        return this.publisher;
    }
        
    public int getYear(){
        return this.year;
    }
    
    public int getNumPages(){
        return this.numPages;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public double getRating() {
        return rating;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    //@JsonFormat(pattern = "MM.dd.yyyy 'at' KK:mm:ss a")
    public LocalDateTime getDateCreated(){
        return this.dateCreated;
    }

    //@JsonFormat(pattern = "MM.dd.yyyy 'at' KK:mm:ss a")
    public LocalDateTime getDateModified(){
        return this.dateModified;
    }


    /*  Setters */

    public void setTitle(String title){
        this.title = title;
        this.dateModified = LocalDateTime.now();
    }

    public void setAuthor(String author) {
        this.author = author;
        this.dateModified = LocalDateTime.now();
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
        this.dateModified = LocalDateTime.now();
    }

    public void setYear(int year) {
        this.year = year;
        this.dateModified = LocalDateTime.now();
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
        this.dateModified = LocalDateTime.now();
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatedDate = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' KK:mm:ss a");

        return this.title + ", by " + this.author + 
        "\n    Published by " + this.publisher + ", " + this.numPages + " pages, " + this.year + " (current edtion)." +
        "\n    ISBN: " + this.isbn +
        "\n    Rated " + this.rating + " out of 5 " + 
        "\n    Other information: " + 
        "\n    -- " + this.otherInfo + 
        "\n    Created: " + this.dateCreated.format(formatedDate) + 
        "\n    Last modified: " + dateModified.format(formatedDate);
    }
    
}

