package com.library.mgmnt.book.service;

import com.library.mgmnt.book.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);
    Book retrieveBookByBookTitleAndAuthor(String bookTitle, String author);
    List<Book> retrieveAllBooks();
    void deleteBook(String bookTitle, String author);
    Book borrowBook(String bookTitle, String author, String studentNo);
    Book returnBook(String bookTitle, String author, String studentNo);
}
