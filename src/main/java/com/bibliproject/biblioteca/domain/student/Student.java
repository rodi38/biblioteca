package com.bibliproject.biblioteca.domain.student;

import com.bibliproject.biblioteca.domain.loan.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    private Long id;
    private String fullName;
    private Email email;
    private List<Loan> loans;
    private int borrowedBooksCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean deleted;

    public void markDeleted() {
        deleted = true;
        deletedAt = LocalDateTime.now();
    }
}
