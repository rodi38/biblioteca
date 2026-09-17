package com.bibliproject.biblioteca.infrastructure.persistence.student;

import com.bibliproject.biblioteca.infrastructure.persistence.loan.LoanJpaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Student")
@Table(name = "students")
public class StudentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    private String fullName;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LoanJpaEntity> loans;

    private int borrowedBooksCount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
