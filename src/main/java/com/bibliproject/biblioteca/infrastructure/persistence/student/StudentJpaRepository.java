package com.bibliproject.biblioteca.infrastructure.persistence.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentJpaRepository extends JpaRepository<StudentJpaEntity, Long> {

    @Query("SELECT s FROM Student s WHERE s.isDeleted = false")
    Page<StudentJpaEntity> findAllNotDeleted(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.isDeleted = false AND (s.fullName LIKE %:search% " +
            "OR s.email LIKE %:search% )")
    Page<StudentJpaEntity> findAllNotDeletedAndMatchesSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.isDeleted = true")
    Page<StudentJpaEntity> findAllDeleted(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.isDeleted = true AND (s.fullName LIKE %:search% " +
            "OR s.email LIKE %:search% )")
    Page<StudentJpaEntity> findAllDeletedAndMatchesSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.id = :id AND s.isDeleted = false")
    Optional<StudentJpaEntity> findByIdAndNotDeleted(@Param("id") Long id);

    Optional<StudentJpaEntity> findByUserId(Long userId);
}
