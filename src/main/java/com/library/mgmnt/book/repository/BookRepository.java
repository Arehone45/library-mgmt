package com.library.mgmnt.book.repository;

import com.library.mgmnt.book.Book;
import com.library.mgmnt.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByBookTitleAndAuthor(String bookTitle , String author);
    List<Book> findBookByBorrowedBy(Student borrowedBy);
}
