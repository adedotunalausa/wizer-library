package com.wizer.wizerbookapp.controller;

import com.wizer.wizerbookapp.dto.input.CategoryInputDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;
import com.wizer.wizerbookapp.service.BookCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/library")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LibraryController extends Controller {

    private final BookCategoryService bookCategoryService;

    @PostMapping("/category")
    public BasicResponseDTO addCategory(@RequestBody @Valid CategoryInputDTO categoryInputDTO) {
        return responseWithUpdatedHttpStatus(bookCategoryService.addCategory(categoryInputDTO));
    }

    @PatchMapping("/category/{categoryId}")
    public BasicResponseDTO updateCategory(@PathVariable(name = "categoryId") Long categoryId, @RequestBody @Valid CategoryInputDTO categoryInputDTO) {
        return responseWithUpdatedHttpStatus(bookCategoryService.editCategory(categoryInputDTO, categoryId));
    }

    @GetMapping("/category")
    public BasicResponseDTO getAllCategories() {
        return responseWithUpdatedHttpStatus(bookCategoryService.getAllCategories());
    }
}
