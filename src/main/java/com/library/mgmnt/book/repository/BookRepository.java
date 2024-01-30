package com.library.mgmnt.book.repository;

import com.library.mgmnt.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByBookTitleAndAuthor(String bookTitle , String author);
}
