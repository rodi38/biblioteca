package com.bibliproject.biblioteca.domain.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Book book;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_student")
    private Student student;
    private Date loanDate;
    private Date returnDate;

}
