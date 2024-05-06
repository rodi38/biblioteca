package com.bibliproject.biblioteca.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Min(value = 0, message = "O stock precisa ser um número válido e positivo.")
    @Max(value = 20,message = "O stock precisa ser um número positivo abaixo de 20" )
    private int stockQuantity;

    @NotBlank(message = "O título é obrigatório")
    @Size(min=3, max=200,  message = "O título deve ter entre 3 e 200 caracteres")
    private String title;

    @NotBlank(message = "O nome do autor é obrigatório")
    @Size(min=3, max=80, message = "O nome do autor deve ter entre 3 e 80 caracteres")
    private String author;

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min=3, max=30,  message = "O nome da categoria deve ter entre 3 e 30 caracteres")
    private String category;

    @NotBlank(message = "O isbn é obrigatório")
    private String isbn;

    @NotBlank(message = "O nome da editora é obrigatório")
    @Size(min=3, max=30,  message = "O nome da editora deve ter entre 3 e 30 caracteres")
    private String publisher;

    @Min(value = 1900, message = "Ano de publicação deve ser de 1900 para cima.")
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