package com.bibliproject.biblioteca.domain.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "not-an-email", "missing-at.com", "no-domain@", "@no-user.com"})
    void rejectsInvalidValues(String value) {
        assertThatThrownBy(() -> new Email(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O email deve ser válido");
    }

    @Test
    void storesTheGivenValue() {
        Email email = new Email("aluno@escola.com");

        assertThat(email.value()).isEqualTo("aluno@escola.com");
        assertThat(email.toString()).isEqualTo("aluno@escola.com");
    }

    @Test
    void equalsAndHashCodeAreBasedOnValue() {
        Email a = new Email("aluno@escola.com");
        Email b = new Email("aluno@escola.com");
        Email c = new Email("outro@escola.com");

        assertThat(a).isEqualTo(b).hasSameHashCodeAs(b);
        assertThat(a).isNotEqualTo(c);
        assertThat(a).isNotEqualTo(null);
        assertThat(a).isNotEqualTo("aluno@escola.com");
        assertThat(a).isEqualTo(a);
    }
}
