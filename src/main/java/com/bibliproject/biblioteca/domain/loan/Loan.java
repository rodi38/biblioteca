package com.bibliproject.biblioteca.domain.loan;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {

    private Long id;
    private Book book;
    private Student student;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private LocalDateTime limitDate;
    private LocalDateTime deletedAt;
    private boolean deleted;

    public boolean isOverdue() {
        return returnDate == null && LocalDateTime.now().isAfter(limitDate);
    }

    public void markReturned() {
        returnDate = LocalDateTime.now();
    }

    public void markDeleted() {
        deleted = true;
        deletedAt = LocalDateTime.now();
    }
}
