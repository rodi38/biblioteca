package com.bibliproject.biblioteca.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;

    @OneToMany(mappedBy = "student")
    private List<Loan> loans;
}
