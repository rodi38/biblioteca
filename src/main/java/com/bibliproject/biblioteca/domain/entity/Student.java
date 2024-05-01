package com.bibliproject.biblioteca.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome completo é obrigatório")
    private String fullName;

    @NotBlank(message = "O email é obrigatório")
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Loan> loans;

    @Max(value = 12, message = "The max number of barrowed books is 12.")
    private int borrowedBooksCount;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public LocalDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

}