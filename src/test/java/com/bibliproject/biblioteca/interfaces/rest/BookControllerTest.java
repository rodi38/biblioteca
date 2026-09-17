package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.book.CreateBookUseCase;
import com.bibliproject.biblioteca.application.book.DeleteBookUseCase;
import com.bibliproject.biblioteca.application.book.FindBookUseCase;
import com.bibliproject.biblioteca.application.book.ListBooksUseCase;
import com.bibliproject.biblioteca.application.book.ListDeletedBooksUseCase;
import com.bibliproject.biblioteca.application.book.UpdateBookUseCase;
import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.Isbn;
import com.bibliproject.biblioteca.exception.book.BookCurrentlyLoanedException;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import com.bibliproject.biblioteca.exception.book.BookOutOfStockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListBooksUseCase listBooksUseCase;

    @MockBean
    private ListDeletedBooksUseCase listDeletedBooksUseCase;

    @MockBean
    private FindBookUseCase findBookUseCase;

    @MockBean
    private CreateBookUseCase createBookUseCase;

    @MockBean
    private UpdateBookUseCase updateBookUseCase;

    @MockBean
    private DeleteBookUseCase deleteBookUseCase;

    private static Book aBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert Martin");
        book.setCategory("Tech");
        book.setIsbn(new Isbn("123"));
        book.setPublisher("Prentice Hall");
        book.setPublishedYear(2008);
        book.setStockQuantity(5);
        return book;
    }

    @Test
    void findByIdReturnsBookWhenFound() throws Exception {
        when(findBookUseCase.execute(1L)).thenReturn(aBook());

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void findByIdReturns404WhenBookNotFound() throws Exception {
        when(findBookUseCase.execute(1L)).thenThrow(new BookNotFoundException(1L));

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void createReturns400WhenBodyFailsValidation() throws Exception {
        String invalidBody = "{\"stockQuantity\":-1,\"title\":\"\",\"author\":\"\",\"category\":\"\",\"isbn\":\"\",\"publisher\":\"\",\"publishedYear\":1000}";

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void createReturns400WhenDataIntegrityIsViolated() throws Exception {
        when(createBookUseCase.execute(any(Book.class))).thenThrow(new DataIntegrityViolationException("duplicate isbn"));
        String validBody = "{\"stockQuantity\":1,\"title\":\"Title\",\"author\":\"Author\",\"category\":\"Category\",\"isbn\":\"123\",\"publisher\":\"Publisher\",\"publishedYear\":2020}";

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void findByIdReturns400WhenBookIsOutOfStock() throws Exception {
        when(findBookUseCase.execute(1L)).thenThrow(new BookOutOfStockException("Livro fora de estoque."));

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void deleteReturnsNoContentWhenSuccessful() throws Exception {
        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteReturns400WhenBookIsCurrentlyLoaned() throws Exception {
        org.mockito.Mockito.doThrow(new BookCurrentlyLoanedException("O livro está emprestado, não é possivel deletar."))
                .when(deleteBookUseCase).execute(anyLong());

        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
}
