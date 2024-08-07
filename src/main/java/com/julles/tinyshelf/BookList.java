/*
 * This software is free to use
 * @author julles
 */

package com.julles.tinyshelf;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BookList {

    final String homeDir;
    private List<Book> updatedBookList;

    public BookList(){
    
        homeDir = System.getProperty("user.home");

    }


    public ObjectMapper mapper(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper;
    }


    public List<Book> returnBookList(){

        List<Book> bookList = new ArrayList<Book>();
        
        try {
            // create object mapper instance
            ObjectMapper objectMapper = this.mapper();
        
            // convert JSON array to list of books
            bookList = new ArrayList<Book>(Arrays.asList(objectMapper.readValue(Paths.get(homeDir + "/.booklist.json").toFile(), Book[].class)));
        
        } catch (Exception ex) {
            //ex.printStackTrace();
            //if list is empty or doesn't exist, populate/create
            if (bookList.isEmpty()) {
                // make the file location customizable
                System.out.println("\nNote: Your book list was empty or not yet created.\nThe (hidden) file can be found at /home/*local user*/.booklist.json.\n");
            }
            
        }

        return bookList;

    }


    public void AddNewBook(String title, String author, String publisher, int year, int numPages, String isbn, double rating, String moreInfo){

        try {

            List<Book> savedBooks = returnBookList();

            Book newBook = new Book(title, author, publisher, year, numPages, isbn, rating, moreInfo); 

            // add book to list
            savedBooks.add(newBook);

            ObjectMapper objectMapper = this.mapper();;
        
            // convert books object to JSON file
            objectMapper.writeValue(Paths.get(homeDir + "/.booklist.json").toFile(), savedBooks);
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public List<Book> findBook(String searchTerm){

        //  list
        List<Book> bookList = returnBookList();
        List<Book> searchFinds = new ArrayList<Book>();

        for (Book book : bookList) {
            
            if (book.getTitle().contains(searchTerm) || book.getAuthor().contains(searchTerm)) {
                searchFinds.add(book);
            }
            
        }

        return searchFinds;

    }


    public void updateBook(Book book, String newData, int field){


            List<Book> bookList = this.returnBookList();
            Book toRemove = book;

            for (Book item : bookList) {
                if (item.toString().equals(book.toString())) {
                    toRemove = item;
                }
            }

            bookList.remove(toRemove);

            Book updatedBook = book;

            switch (field) {
                case 0:
                    updatedBook.setTitle(newData);
                    break;
                case 1:
                    updatedBook.setAuthor(newData);
                    break;
                case 2:
                    updatedBook.setPublisher(newData);
                    break;
                case 3:
                    updatedBook.setYear(Integer.valueOf(newData));
                    break;
                case 4:
                    updatedBook.setNumPages(Integer.valueOf(newData));
                    break;
                case 5:
                    updatedBook.setIsbn(newData);
                    break;
                case 6:
                    updatedBook.setRating(Double.valueOf(newData));
                    break;
                case 7:
                    updatedBook.setOtherInfo(newData);
                    break;     

                default:
                    break;
            }

            updatedBook.setDateModified(LocalDateTime.now());

            bookList.add(updatedBook);

                
        try {

            ObjectMapper objectMapper = this.mapper();;
        
            // convert books object to JSON file
            objectMapper.writeValue(Paths.get(homeDir + "/.booklist.json").toFile(), bookList);
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    public void removeBook(Book book, List<Book> booklist){

        // remove a preselected book from list

        //if (booklist.contains(book)) {
            booklist.remove(book);
            this.updatedBookList = booklist;
        //}
                
        try {

            // add book to list
            ObjectMapper objectMapper = this.mapper();;
        
            // convert books object to JSON file
            objectMapper.writeValue(Paths.get(homeDir + "/.booklist.json").toFile(), updatedBookList);
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
    }

}
