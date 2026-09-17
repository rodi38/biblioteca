package com.bibliproject.biblioteca.domain.book;

import java.util.Objects;

public final class Isbn {

    private final String value;

    public Isbn(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O isbn é obrigatório");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Isbn)) return false;
        return value.equals(((Isbn) o).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
