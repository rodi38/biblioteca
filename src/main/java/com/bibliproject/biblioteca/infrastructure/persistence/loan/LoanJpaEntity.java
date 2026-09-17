package com.bibliproject.biblioteca.infrastructure.persistence.loan;

import com.bibliproject.biblioteca.infrastructure.persistence.book.BookJpaEntity;
import com.bibliproject.biblioteca.infrastructure.persistence.student.StudentJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Loan")
@Table(name = "loans")
public class LoanJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookJpaEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentJpaEntity student;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime loanDate;

    private LocalDateTime returnDate;

    @Column(nullable = false)
    private LocalDateTime limitDate;

    private LocalDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
