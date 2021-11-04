package com.wizer.wizerbookapp.service;

import com.wizer.wizerbookapp.dto.input.BookInputDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;

public interface BookService {

    BasicResponseDTO addBook(BookInputDTO bookInputDTO);

    BasicResponseDTO updateBook(BookInputDTO bookInputDTO, Long bookId);

    BasicResponseDTO getAllBooks();

    BasicResponseDTO addBookToCategory(Long bookId, Long categoryId);

}
