package com.bibliproject.biblioteca.domain.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IsbnTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void rejectsNullOrBlankValue(String value) {
        assertThatThrownBy(() -> new Isbn(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O isbn é obrigatório");
    }

    @Test
    void storesTheGivenValue() {
        Isbn isbn = new Isbn("978-3-16-148410-0");

        assertThat(isbn.value()).isEqualTo("978-3-16-148410-0");
        assertThat(isbn.toString()).isEqualTo("978-3-16-148410-0");
    }

    @Test
    void equalsAndHashCodeAreBasedOnValue() {
        Isbn a = new Isbn("978-3-16-148410-0");
        Isbn b = new Isbn("978-3-16-148410-0");
        Isbn c = new Isbn("000-0-00-000000-0");

        assertThat(a).isEqualTo(b).hasSameHashCodeAs(b);
        assertThat(a).isNotEqualTo(c);
        assertThat(a).isNotEqualTo(null);
        assertThat(a).isNotEqualTo("978-3-16-148410-0");
        assertThat(a).isEqualTo(a);
    }
}
