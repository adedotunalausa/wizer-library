package com.wizer.wizerbookapp.service.ServiceImplementation;

import com.wizer.wizerbookapp.dto.input.BookInputDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;
import com.wizer.wizerbookapp.enums.Status;
import com.wizer.wizerbookapp.exception.ResourceNotFoundException;
import com.wizer.wizerbookapp.model.Book;
import com.wizer.wizerbookapp.model.BookCategory;
import com.wizer.wizerbookapp.repository.BookCategoryRepository;
import com.wizer.wizerbookapp.repository.BookRepository;
import com.wizer.wizerbookapp.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;

    private final BookCategoryRepository bookCategoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public BasicResponseDTO addBook(BookInputDTO bookInputDTO) {
        Book book = modelMapper.map(bookInputDTO, Book.class);
        bookRepository.save(book);
        log.info("Book successfully added to library");
        return new BasicResponseDTO(Status.CREATED, book);
    }

    @Override
    public BasicResponseDTO updateBook(BookInputDTO bookInputDTO, Long bookId) {
        Book bookFromDatabase = getBookById(bookId);
        if (Objects.nonNull(bookInputDTO.getName())) bookFromDatabase.setName(bookInputDTO.getName());
        if (Objects.nonNull(bookInputDTO.getDescription())) bookFromDatabase.setDescription(bookInputDTO.getDescription());
        if (bookInputDTO.getQuantity() != bookFromDatabase.getQuantity()) bookFromDatabase.setQuantity(bookInputDTO.getQuantity());

        bookRepository.save(bookFromDatabase);
        log.info("Book successfully updated");
        return new BasicResponseDTO(Status.SUCCESS, bookFromDatabase);
    }

    @Override
    public BasicResponseDTO getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return new BasicResponseDTO(Status.SUCCESS, bookList);
    }

    @Override
    public BasicResponseDTO addBookToCategory(Long bookId, Long categoryId) {
        Book updatedBook = updateBookCategory(bookId, categoryId);
        return new BasicResponseDTO(Status.SUCCESS, updatedBook);
    }

    private Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Book not found"));
    }

    private Book updateBookCategory(Long bookId, Long categoryId) {
        Book book = getBookById(bookId);
        BookCategory category = getCategoryById(categoryId);
        setCategoryIdForBook(book, category);
        addBookToCategory(book, category);
        return book;
    }

    private void setCategoryIdForBook(Book book, BookCategory category) {
        book.setCategoryId(category.getId());
        bookRepository.save(book);
    }

    private void addBookToCategory(Book book, BookCategory category) {
        category.getBooks().add(book);
        bookCategoryRepository.save(category);
    }

    private BookCategory getCategoryById(Long categoryId) {
        return bookCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Book not found"));
    }
}
