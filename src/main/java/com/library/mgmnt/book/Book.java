package com.library.mgmnt.book;

import com.library.mgmnt.student.entity.Student;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bookTitle;
    private String author;
    private Boolean isBorrowed;
    @ManyToOne
    @JoinColumn(name = "student_studentNo")
    private Student borrowedBy;
}
