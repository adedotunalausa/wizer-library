package com.wizer.wizerbookapp.service.ServiceImplementation;

import com.wizer.wizerbookapp.dto.input.BookInputDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;
import com.wizer.wizerbookapp.model.Book;
import com.wizer.wizerbookapp.repository.BookCategoryRepository;
import com.wizer.wizerbookapp.repository.BookRepository;
import com.wizer.wizerbookapp.repository.UserRepository;
import com.wizer.wizerbookapp.service.UserService;
import com.wizer.wizerbookapp.util.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplementationTest {

    private ModelMapper modelMapper;

    private JwtUtils jwtUtils;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookCategoryRepository bookCategoryRepository;

    @Mock
    private UserRepository userRepository;

    private BookServiceImplementation bookServiceImplementationTest;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        bookServiceImplementationTest = new BookServiceImplementation(bookRepository, bookCategoryRepository,
                userRepository, modelMapper, jwtUtils);
    }

    @Test
    void canAddBook() {
        BookInputDTO dto = getBook();

        BasicResponseDTO response = bookServiceImplementationTest.addBook(dto);

        assertThat(response).isNotNull();
    }

    @Test
    void canUpdateBook() {
    }

    @Test
    void canGetAllBooks() {
    }

    @Test
    void canAddBookToCategory() {
    }

    @Test
    void canAddBookToFavorite() {
    }

    private BookInputDTO getBook() {
        return new BookInputDTO("TestName", "testDescription", 8, 2L);
    }
}