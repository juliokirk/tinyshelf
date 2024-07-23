/*
 * This software is free to use
 * @author julles
 */

package com.julles.tinyshelf;

import java.util.Scanner;
import java.util.List;

public class Prompts {

    private String title;
    private String author;
    private String publisher;
    private int year;
    private int numPages;

    private String continueOrNot;

    private String field;

    private Scanner scanner = new Scanner(System.in);

    public Prompts(){
        
    }

    //add validations, so that typing invalid info (like string instead of int) on prompts
    //restarts them instead of throwing an error;
    //this step is necessary for the implementation of searches, 
    //updating specific fields (simple CRUD in general)

    // Greetings
    public void displayGreetings(){

        System.out.println("\nGreetings!\n>>>> Welcome to tinyShelf <<<<");
        
    }

    public String returnExit(){
        return this.field;
    }
    
    // Title
    public void askTitle(){

        while (true) {
            System.out.print("\nTitle: ");
            String title = scanner.nextLine();
            if (title.isEmpty()) {
                System.out.print("All fields are required.\n");
                continue;
            } 
            this.title = title;
            this.field = title;
            break;   
        }
        
    }

    public String returnTitle(){

        return this.title;

    }


    //Author
    public void askAuthor(){

        while (true) {
            System.out.print("Author: ");
            String author = scanner.nextLine();
            if (author.isEmpty()) {
                System.out.println("All fields are required.\n");
                continue;
            }
            this.author = author;
            this.field = author;
            break;   
        }
        
    }

    public String returnAuthor(){

        return this.author;

    }


    //Publisher
    public void askPublisher(){

        while (true) {
            System.out.print("Publisher: ");
            String publisher = scanner.nextLine();
            if (publisher.isEmpty()) {
                System.out.println("All fields are required.\n");
                continue;
            }
            this.publisher = publisher;
            this.field = publisher;
            break;   
        }
        
    }

    public String returnPublisher(){

        return this.publisher;

    }


    //year
    public void askYear(){

        while (true) {
            System.out.print("Publication year: ");
            String year = scanner.nextLine();

            if (year.isEmpty()) {
                System.out.print("All fields are required!\n");
                System.out.println();
                continue;
            } else if (year.equals("e")) {
                this.field = year;
                break;
            } else {
                this.year = Integer.valueOf(year);
                break; 
            }
              
        }
        
    }

    public int returnYear(){

        return this.year;

    }


    //pages
    public void askNumPages(){

        while (true) {
            System.out.print("Number of pages: ");
            String pages = scanner.nextLine();
            if (pages.isEmpty()) {
                System.out.print("All fields are required!\n");
                System.out.println();
                continue;
            } else if (pages.equals("e")) {
                this.field = pages;
                break;
            } else {
                this.numPages = Integer.valueOf(pages);
                break; 
            }
        }
        
    }

    public int returnNumPages(){

        return this.numPages;

    }


    //continue prompt
    public void continueOrNot(){

        while (true) {
            System.out.println("\nContinue? y/n");
            String answer = scanner.nextLine();
            System.out.println();
            if (answer.isEmpty()) {
                continue;
            }
            this.continueOrNot = answer;
            break;
        }

    }

    public String returnContinueOrNot(){
        return this.continueOrNot;
    }


    // Send new book to list
    public void sendToList(){

        BookList bookList = new BookList();
        bookList.AddNewBook(returnTitle(), returnAuthor(), returnPublisher(), returnYear(), returnNumPages());
    
    }

    public void newBookPrompt(){

        while(true) {
        
            System.out.print("\nPlease type information as asked. All fields are required.\nPress (e) to exit.\n");

            this.askTitle();
            if (this.field.equals("e")) {
                break;
            }
            this.askAuthor();
            if (this.field.equals("e")) {
                break;
            }
            this.askPublisher();
            if (this.field.equals("e")) {
                break;
            }
            this.askYear();
            if (this.field.equals("e")) {
                break;
            }
            this.askNumPages();
            if (this.field.equals("e")) {
                break;
            }
   
            this.continueOrNot();
   
            String continuePrompt = this.returnContinueOrNot();
   
            if (continuePrompt.equals("y") || continuePrompt.equals("Y")) {
   
                this.sendToList();
                continue;
   
            } else if (continuePrompt.equals("n") || continuePrompt.equals("N")) {
   
               this.sendToList();
               break;
   
            } else {
   
               System.out.print("\nInvalid answer. Saving data and exiting.\n");
               this.sendToList();
               break;
   
            }
            
        }        

    }

    public void searchPrompt(){

        while (true) {
        System.out.println("\nType complete or partial name of the book or author. Search is case sensitive.\nEnter a blank search term to exit.\n");
        String searchTerm = scanner.nextLine();

        if (searchTerm.isEmpty()) {
            break;
        }

        BookList bookList = new BookList();


            List<Book> searchResult = bookList.findBook(searchTerm);
            int numOfResults = searchResult.size();
    
            if (numOfResults == 1) {
                System.out.println("\n1 entry has been found containing '" + searchTerm + "'':");
            } else if (numOfResults > 1) {
                System.out.println("\n" + numOfResults + " entries have been found containing '" + searchTerm + "':\n");
            } else if (numOfResults == 0) {
                System.out.println("\nNo entries have been found containing '" + searchTerm + "''.\n");
            }
            
            for (Book book : searchResult) {
                System.out.println("==> " + book);
            }

            this.continueOrNot();
            String answer = this.returnContinueOrNot();
            
            if (answer.equals("y") || answer.equals("Y")) {
   
                continue;
   
            } else if (answer.equals("n") || answer.equals("N")) {
   
               break;
   
            } else {
   
               System.out.print("\nInvalid answer. Exiting.\n");

               break;
   
            }


        }
       
        
    }

    public void mainMenuPrompt(){
        // Ask user what they wish to do (add new book, find book, update book, delete book)
   
        while (true) {

            System.out.println("\nWhat do you wish to do?\nType (a) to add a new book or (s) to use the search function. Type (e) to exit program.");
            String answer = scanner.nextLine();
    

            if (answer.equals("a") || answer.equals("A")) {

                this.newBookPrompt();
                continue;
    
            } else if (answer.equals("s") || answer.equals("S")) {
    
                this.searchPrompt();
                continue;
    
            } else if (answer.equals("e") || answer.equals("E")) {
                
                System.out.print("\nSee you next time!\n");
                break;
    
            } else {
                System.out.println("\nInvalid option. Please try again.\n");
                continue;
            }

        }

    }

}
