package com.library.mgmnt.book.controller;

import com.library.mgmnt.book.Book;
import com.library.mgmnt.book.repository.BookRepository;
import com.library.mgmnt.book.service.serviceImpl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-app")
public class BookController {

    private final BookServiceImpl bookService;
    private final BookRepository bookRepository;

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        Book savedBook = bookRepository.save(book);

        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/getBookByAuthor/{bookTitle}/{author}")
    public Book getBook(@PathVariable String author,@PathVariable String bookTitle) {

        return bookService.retrieveBookByBookTitleAndAuthor(bookTitle,author);
    }

    @PostMapping("/borrowBook/{bookTitle}/{author}/{studentNo}")
    public Book borrowBook(@PathVariable String bookTitle, @PathVariable String author, @PathVariable String studentNo) {

        return bookService.borrowBook(bookTitle,author,studentNo);
    }

    @PostMapping("/returnBook/{bookTitle}/{author}/{studentNo}")
    public Book returnBook(@PathVariable String bookTitle, @PathVariable String author, @PathVariable String studentNo) {

        return bookService.returnBook(bookTitle,author,studentNo);
    }

    @DeleteMapping("/deleteBook/{bookTitle}/{author}")
    public void deleteBook(@PathVariable String bookTitle, @PathVariable String author) {

        bookService.deleteBook(bookTitle, author);
    }

    @GetMapping("/getAll")
    public List<Book> getAll() {
        return bookService.retrieveAllBooks();
    }
}
