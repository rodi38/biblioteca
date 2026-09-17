package com.bibliproject.biblioteca.domain.auth;

public interface TokenProvider {

    String generate(AuthenticatedPrincipal principal);

    AuthenticatedPrincipal parse(String token);
}
