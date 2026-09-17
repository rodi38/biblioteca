package com.bibliproject.biblioteca.infrastructure.persistence.student;

import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final StudentJpaRepository studentJpaRepository;

    public StudentRepositoryImpl(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public Page<Student> findAllNotDeleted(Pageable pageable) {
        return studentJpaRepository.findAllNotDeleted(pageable).map(StudentPersistenceMapper::toDomain);
    }

    @Override
    public Page<Student> findAllNotDeletedAndMatchesSearch(String search, Pageable pageable) {
        return studentJpaRepository.findAllNotDeletedAndMatchesSearch(search, pageable).map(StudentPersistenceMapper::toDomain);
    }

    @Override
    public Page<Student> findAllDeleted(Pageable pageable) {
        return studentJpaRepository.findAllDeleted(pageable).map(StudentPersistenceMapper::toDomain);
    }

    @Override
    public Page<Student> findAllDeletedAndMatchesSearch(String search, Pageable pageable) {
        return studentJpaRepository.findAllDeletedAndMatchesSearch(search, pageable).map(StudentPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Student> findByIdAndNotDeleted(Long id) {
        return studentJpaRepository.findByIdAndNotDeleted(id).map(StudentPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Student> findByUserId(Long userId) {
        return studentJpaRepository.findByUserId(userId).map(StudentPersistenceMapper::toDomain);
    }

    @Override
    public Student save(Student student) {
        StudentJpaEntity saved = studentJpaRepository.save(StudentPersistenceMapper.toJpaEntity(student));
        return StudentPersistenceMapper.toDomain(saved);
    }
}
