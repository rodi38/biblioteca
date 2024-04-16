package com.bibliproject.biblioteca.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_book")
    @JsonBackReference
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_student")
    @JsonBackReference
    private Student student;

    private Date loanDate;

    private Date returnDate;

}