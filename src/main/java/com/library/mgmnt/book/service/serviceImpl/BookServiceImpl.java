package com.library.mgmnt.book.service.serviceImpl;

import com.library.mgmnt.book.Book;
import com.library.mgmnt.book.exception.BookException;
import com.library.mgmnt.book.repository.BookRepository;
import com.library.mgmnt.book.service.BookService;
import com.library.mgmnt.student.entity.Student;
import com.library.mgmnt.student.service.serviceImpl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StudentServiceImpl studentService;

    @Override
    public Book addBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public Book retrieveBookByBookTitleAndAuthor(String bookTitle, String author) {

        Book book = bookRepository.findByBookTitleAndAuthor(bookTitle, author);

        if (book == null)
        {
            throw new BookException("Book could not be found.");
        } else {
            return book;
        }
    }

    @Override
    public List<Book> retrieveAllBooks() {

        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(String bookTitle, String author) {

        Book book = retrieveBookByBookTitleAndAuthor(bookTitle, author);
        Long bookId = book.getId();

        log.info("Book found: " + book.toString());

        bookRepository.deleteById(bookId);
        log.info("Book deleted.");
    }

    @Override
    public Book borrowBook(String bookTitle, String author , String studentNo) {

        Book reqdBook = retrieveBookByBookTitleAndAuthor(bookTitle, author);
        log.info("Book found: " + reqdBook.toString());

        Student student = studentService.getStudentByStudentNo(studentNo);
        log.info("Student found: " + student.toString());

        if (reqdBook.getIsBorrowed() == null || !reqdBook.getIsBorrowed()) {
            reqdBook.setIsBorrowed(true);
            reqdBook.setBorrowedBy(student);
        } else {
            throw new BookException("Required book is already borrowed");
        }

        return bookRepository.save(reqdBook);
    }

    @Override
    public Book returnBook(String bookTitle, String author, String studentNo) {

        Student student = studentService.getStudentByStudentNo(studentNo);
        log.info("Student found is: " + student.toString());

        Book book = retrieveBookByBookTitleAndAuthor(bookTitle, author);
        log.info("Book found is: " + book.toString());

        if (book.getBorrowedBy().equals(student) && book.getIsBorrowed())
        {
            book.setBorrowedBy(null);
            book.setIsBorrowed(false);
        } else if (!book.getIsBorrowed()) {
            throw new BookException("Book is not borrowed or book now borrowed by: " + student.getName() + ".");
        }

        return bookRepository.save(book);
    }
}
