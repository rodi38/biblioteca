package com.bibliproject.biblioteca.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonManagedReference
    private Student student;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime loanDate;


    private LocalDateTime returnDate;

    @Column(nullable = false)
    private LocalDateTime limitDate;

    public LocalDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;


}