package com.bibliproject.biblioteca.domain.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Student student;
    private Date loanDate;
    private Date returnDate;

}
