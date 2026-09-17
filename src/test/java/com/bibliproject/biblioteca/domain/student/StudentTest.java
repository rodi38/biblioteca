package com.bibliproject.biblioteca.domain.student;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    @Test
    void markDeletedFlagsStudentAndSetsDeletedAt() {
        Student student = new Student();

        student.markDeleted();

        assertThat(student.isDeleted()).isTrue();
        assertThat(student.getDeletedAt()).isNotNull();
    }
}
