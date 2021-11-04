package com.wizer.wizerbookapp.controller;

import com.wizer.wizerbookapp.dto.input.BookInputDTO;
import com.wizer.wizerbookapp.dto.input.CategoryInputDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;
import com.wizer.wizerbookapp.service.BookCategoryService;
import com.wizer.wizerbookapp.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/library")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LibraryController extends Controller {

    private final BookCategoryService bookCategoryService;

    private final BookService bookService;

    @PostMapping("/category")
    public BasicResponseDTO addCategory(@RequestBody @Valid CategoryInputDTO categoryInputDTO) {
        return responseWithUpdatedHttpStatus(bookCategoryService.addCategory(categoryInputDTO));
    }

    @PatchMapping("/category/{categoryId}")
    public BasicResponseDTO updateCategory(@PathVariable(name = "categoryId") Long categoryId, @RequestBody @Valid CategoryInputDTO categoryInputDTO) {
        return responseWithUpdatedHttpStatus(bookCategoryService.updateCategory(categoryInputDTO, categoryId));
    }

    @GetMapping("/category")
    public BasicResponseDTO getAllCategories() {
        return responseWithUpdatedHttpStatus(bookCategoryService.getAllCategories());
    }

    @PostMapping("/book")
    public BasicResponseDTO addBook(@RequestBody @Valid BookInputDTO bookInputDTO) {
        return responseWithUpdatedHttpStatus(bookService.addBook(bookInputDTO));
    }

    @PatchMapping("/book/{bookId}")
    public BasicResponseDTO updateBook(@PathVariable(name = "bookId") Long bookId, @RequestBody @Valid BookInputDTO bookInputDTO) {
        return responseWithUpdatedHttpStatus(bookService.updateBook(bookInputDTO, bookId));
    }

    @GetMapping("/book")
    public BasicResponseDTO getAllBooks() {
        return responseWithUpdatedHttpStatus(bookService.getAllBooks());
    }

    @PatchMapping("/book/{bookId}/{categoryId}")
    public BasicResponseDTO addBookToCategory(@PathVariable(name = "bookId") Long bookId, @PathVariable(name = "categoryId") Long categoryId) {
        return responseWithUpdatedHttpStatus(bookService.addBookToCategory(bookId, categoryId));
    }

    @PatchMapping("/book/favorite/{bookId}")
    public BasicResponseDTO addBookToFavorite(@PathVariable(name = "bookId") Long bookId, @RequestHeader(name = "Authorization") String token) {
        return responseWithUpdatedHttpStatus(bookService.addBookToFavorite(bookId, token));
    }

}
