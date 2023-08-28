package com.bagas;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/*
 * README
 * ======
 *
 * This is simple source code for bookstore application.
 * The functional requirement that you needed to help you understand the user story has been written.
 *
 * Your mission is :
 *
 * 1. Complete these source code at "PLACE YOUR CODE HERE" parts and run them without any error.
 * 2. The result has to be:
 *    1:true
 *    2:true
 *    3:true
 *    4:true
 *    5:true
 * 3. Send the source code file with your answers.
 */
class Algoshop {

    public static void main(String[] args) {
        Shop shop  = new Shop();
        Book[] books = new Book[]{
                new Book("The Fellowship of the Ring", "J.R.R. Tolkien"),
                new Book("The Two Towers", "J.R.R. Tolkien"),
                new Book("The Return of the King", "J.R.R. Tolkien"),
                new Book("The Hobbit", "J.R.R. Tolkien"),
                new Book("Harry Potter and the Sorcerer\"s Stone", "J.K. Rowling"),
                new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling"),
                new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling"),
                new Book("Harry Potter and the Goblet of Fire", "J.K. Rowling"),
                new Book("Harry Potter and the Order of the Phoenix", "J.K. Rowling"),
                new Book("Harry Potter and the Half-Blood Prince", "J.K. Rowling"),
                new Book("Harry Potter and the Deathly Hallows", "J.K. Rowling"),
        };
        for (Book book: books) {
            shop.bookAdd(book);
        }
        // Ahmad goes to the bookstore.
        // He looks for a book title "Two Towers"
        // If the book is there, he puts it on book cart.
        Person ahmad = new Person("Ahmad Ramadhan");
        boolean available = shop.bookIsAvailable("The Two Towers");
        if (available) {
            Book book = shop.bookGet("The Two Towers");
            ahmad.addToBag(book);
            System.out.println("1: true");
        } else {
            System.out.println("1: false");
        }
        System.out.println("\n");
        // Ahmad goes to bookcase collection from author J.K. Rowling
        // He looks for a book title "Harry Potter and The Goblet of Fire" from author J.K Rowling
        // If the book is there, he puts it on book cart.
        List<String> rowlingBooks = shop.bookListByAuthor("J.K. Rowling");
        if (rowlingBooks.contains("Harry Potter and the Goblet of Fire")) {
            Book book = shop.bookGet("Harry Potter and the Goblet of Fire");
            ahmad.addToBag(book);
            System.out.println("2: true");
        } else {
            System.out.println("2: false");
        }
        System.out.println("\n");
        // Ahmad has finished choose the books and goes to cashier.
        // He checked the bookcart and sees that there are 2 books on bookcart
        if (ahmad.countBag() == 2) {
            System.out.println("3: true");
        } else {
            System.out.println("3: false");
        }
        System.out.println("\n");
        // Ahmad finished buy the books.
        // Bayu goes to the bookstore.
        // He looks for a book that he only remembers part of the title is "The King" in entire bookcase.
        // After he finds 1 book with title matched, he puts in on bookcart.
        Person bayu = new Person("Bayu Sakti");
        List<String> books_theking = shop.bookListByTitleContains("The King");
        if (books_theking.size() > 0) {
            Book book = shop.bookGet(books_theking.get(0));
            bayu.addToBag(book);
            System.out.println("4: true");
        } else {
            System.out.println("4: false");
        }
        System.out.println("\n");
        // Accidentally, He looks new arrival of the newest Harry potter"s book series.
        // Then he put it on bookcart and turn book "The Return of The King" back to bookcase.
        available = shop.bookIsAvailable("Harry Potter and the Deathly Hallows");
        if (available) {
            Book book = shop.bookGet("Harry Potter and the Deathly Hallows");
            bayu.addToBag(book);
            bayu.removeFromBag("The Return of the King");
        }
        // Bayu has finished choose the books and goes to cashier.
        // He checked the bookcart and sees that there is 1 book on bookcart
        if (bayu.countBag() == 1) {
            System.out.println("5: true");
        } else {
            System.out.println("5: false");
        }
        System.out.println("\n");
        // Bayu finished buy the books.
    }
}

class Shop {
    // PLACE YOUR CODE HERE

    List<Book> listBook = new ArrayList<>();

    public void bookAdd(Book newBook){
        listBook.add(newBook);
    }

    // cek ketersediaan buku
    public boolean bookIsAvailable(String bookTitle){
        boolean bookAvailability = false;
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getTitle().equals(bookTitle)){
                bookAvailability = true;
                break;
            }
        }
        return bookAvailability;
    }

    // mengambil buku
    public Book bookGet(String bookTitle){
        Book getBook = null;
        for (int i = 0; i < listBook.size(); i++) {

            if (listBook.get(i).getTitle().equals(bookTitle)){
                getBook = listBook.get(i);
            }
        }
        return getBook;
    }

    // cek list buku berdasarkan penulis
    public List<String> bookListByAuthor (String author){
        List<String> listBookByAuthor = new ArrayList<>();

        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getAuthor().equals(author)){
                listBookByAuthor.add(listBook.get(i).getTitle());
            }

        }
        return listBookByAuthor;
    }

    // cek list buku berdasarkan judul
    public List<String> bookListByTitleContains(String bookTitle){
        List<String> listBookByTitle = new ArrayList<>();

        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getTitle().toLowerCase(Locale.ROOT).contains(bookTitle.toLowerCase(Locale.ROOT))){
                listBookByTitle.add(listBook.get(i).getTitle());
            }

        }
        return listBookByTitle;
    }
}

class Book {
    // PLACE YOUR CODE HERE
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}

class Person {
    // PLACE YOUR CODE HERE

    private String name;
    private List<Book> bag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBag() {
        return bag;
    }

    public void setBag(List<Book> bag) {
        this.bag = bag;
    }

    public Person(String name) {
        this.name = name;
        bag = new ArrayList<>();

    }

    // memasukkan buku ke keranjang
    public void addToBag(Book book){
        bag.add(book);
    }

    // menghitung buku dalam keranjang
    public int countBag(){
        int bookCount = bag.size();
        return bookCount;
    }

    // mengembalikan buku dari keranjang
    public void removeFromBag(String bookTitle){
        for (int i = 0; i < bag.size(); i++) {
            if (bag.get(i).getTitle().equals(bookTitle)){
                bag.remove(i);
                break;
            }

        }
    }
}