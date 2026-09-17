package com.bibliproject.biblioteca.domain.auth;

public interface CurrentUserProvider {

    AuthenticatedPrincipal getCurrentUser();
}
