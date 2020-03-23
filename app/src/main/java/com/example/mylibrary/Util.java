package com.example.mylibrary;


import java.util.ArrayList;

public class Util {
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> alreadyReadBooks;

    private static int id = 0;

    public Util(){
        if(allBooks == null){
            allBooks = new ArrayList<Book>();
            initAllBooks();

        }
        if(currentlyReadingBooks == null){
            currentlyReadingBooks = new ArrayList<Book>();
        }
        if(wantToReadBooks == null){
            wantToReadBooks = new ArrayList<Book>();

        }
        if(alreadyReadBooks == null){
            alreadyReadBooks = new ArrayList<Book>();
        }
    }



    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getCurrentlyReadBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public boolean addCurrentlyReadingBook (Book book){
        boolean isAdded = currentlyReadingBooks.add(book);  //or return currentlyReadingBooks.add(book) //add method has boolean return type
        return isAdded;
    }

    public boolean addWantToReadBooks (Book book){
        return wantToReadBooks.add(book);
    }
    public boolean addAlreadyReadBooks(Book book){
        return alreadyReadBooks.add(book);
    }

    public boolean removeCurrentlyReadingBooks(Book book){
        return currentlyReadingBooks.remove(book);
    }
    public boolean removeWantToReadBooks(Book book){
        return wantToReadBooks.remove(book);
    }

    private static void initAllBooks(){

        String name = "" ;
        String author = "";
        int pages = 0;
        String imageUrl = "";
        String description = "";

        id++;
        name = "Eloquent JavaScript Second Edition";
        author = "Marijn Haverbeke";
        pages = 472;
        description = "JavaScript lies at the heart of almost every modern web application from social apps to the newest browser-based games. Though simple for beginners to pick up and play with JavaScript is a flexible complex language that you can use to build full-scale applications.";
        imageUrl = "http://www.clker.com/cliparts/3/m/v/Y/E/V/small-red-apple-md.png";
        allBooks.add(new Book(id, name, author,pages, imageUrl,description));

        id++;
        name = "Learning JavaScript Design Patterns";
        imageUrl = "http://www.clker.com/cliparts/3/m/v/Y/E/V/small-red-apple-md.png";
        author = "Addy Osmani";
        pages = 254;
        description = "With Learning JavaScript Design Patterns you'll learn how to write beautiful structured and maintainable JavaScript by applying classical and modern design patterns to the language. If you want to keep your code efficient more manageable and up-to-date with the latest best practices this book is for you.";
        allBooks.add(new Book(id,name, author,pages, imageUrl,description));

        id++;
        name = "Speaking JavaScript";
        author = "Axel Rauschmayer";
        pages = 460;
        imageUrl = "http://www.clker.com/cliparts/3/m/v/Y/E/V/small-red-apple-md.png";
        description = "Like it or not JavaScript is everywhere these days-from browser to server to mobile-and now you too need to learn the language or dive deeper than you have. This concise book guides you into and through JavaScript written by a veteran programmer who once found himself in the same position.";
        allBooks.add(new Book(id,name, author,pages, imageUrl,description));

        id++;
        name = "Programming JavaScript Applications";
        author = "Eric Elliott";
        imageUrl = "http://www.clker.com/cliparts/3/m/v/Y/E/V/small-red-apple-md.png";
        pages = 254;
        description = "Take advantage of JavaScript's power to build robust web-scale or enterprise applications that are easy to extend and maintain. By applying the design patterns outlined in this practical book experienced JavaScript developers will learn how to write flexible and resilient code that's easier-yes easier-to work with as your code base grows.";
        allBooks.add(new Book(id,name, author,pages, imageUrl,description));


        id++;
        name = "Understanding ECMAScript 6";
        author = "Nicholas C. Zakas";
        pages = 352;
        description = "ECMAScript 6 represents the biggest update to the core of JavaScript in the history of the language. In Understanding ECMAScript 6 expert developer Nicholas C. Zakas provides a complete guide to the object types syntax and other exciting changes that ECMAScript 6 brings to JavaScript.";
        imageUrl = "http://www.clker.com/cliparts/3/m/v/Y/E/V/small-red-apple-md.png";
        allBooks.add(new Book(id,name, author,pages, imageUrl,description));

        id++;
        name = "You Don't Know JS";
        imageUrl = "http://www.clker.com/cliparts/3/m/v/Y/E/V/small-red-apple-md.png";
        author = "Kyle Simpson";
        pages = 278;
        description = "No matter how much experience you have with JavaScript odds are you don’t fully understand the language. As part of the \"You Don’t Know JS\" series this compact guide focuses on new features available in ECMAScript 6 (ES6) the latest version of the standard upon which JavaScript is built.";
        allBooks.add(new Book(id,name, author,pages, imageUrl,description));


        id++;
        name = "Git Pocket Guide";
        author = "Richard E. Silverman";
        pages = 234;
        imageUrl = "http://www.clker.com/cliparts/3/m/v/Y/E/V/small-red-apple-md.png";
        description = "This pocket guide is the perfect on-the-job companion to Git the distributed version control system. It provides a compact readable introduction to Git for new users as well as a reference to common commands and procedures for those of you with Git experience.";
        allBooks.add(new Book(id,name, author,pages, imageUrl,description));

        id++;
        name = "Designing Evolvable Web APIs with ASP.NET";
        author = "Glenn Block et al.";
        pages = 538;
        description = "Design and build Web APIs for a broad range of clients—including browsers and mobile devices—that can adapt to change over time. This practical hands-on guide takes you through the theory and tools you need to build evolvable HTTP services with Microsoft’s ASP.NET Web API framework. In the process you’ll learn how design and implement a real-world Web API.";
        imageUrl = "http://www.clker.com/cliparts/3/m/v/Y/E/V/small-red-apple-md.png";;
        allBooks.add(new Book(id,name, author,pages, imageUrl,description));


    }
}
