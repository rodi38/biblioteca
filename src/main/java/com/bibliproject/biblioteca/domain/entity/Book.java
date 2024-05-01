package com.bibliproject.biblioteca.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "O stock precisa ser um numero válido.")
    private int stockQuantity;

    @NotBlank(message = "O titulo é obrigatório")
    private String title;

    @NotBlank(message = "O nome do autor é obrigatório")
    private String author;

    @NotBlank(message = "O nome da categoria é obrigatório")
    private String category;

    @NotBlank(message = "O isbn é obrigatório")
    private String isbn;

    @NotBlank(message = "O nome da editora é obrigatório")
    private String publisher;

    @Min(value = 1900, message = "Ano de publicação de 1900 pra cima.")
    private int publishedYear;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Loan> loans;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public LocalDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}