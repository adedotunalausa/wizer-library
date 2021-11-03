package com.wizer.wizerbookapp.service.ServiceImplementation;

import com.wizer.wizerbookapp.dto.input.CategoryInputDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;
import com.wizer.wizerbookapp.enums.Status;
import com.wizer.wizerbookapp.exception.ResourceNotFoundException;
import com.wizer.wizerbookapp.model.BookCategory;
import com.wizer.wizerbookapp.repository.BookCategoryRepository;
import com.wizer.wizerbookapp.service.BookCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookCategoryServiceImplementation implements BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public BasicResponseDTO addCategory(CategoryInputDTO categoryInputDTO) {
        BookCategory bookCategory = modelMapper.map(categoryInputDTO, BookCategory.class);
        bookCategoryRepository.save(bookCategory);
        log.info("Category {} successfully added", categoryInputDTO.getName());
        return new BasicResponseDTO(Status.CREATED, bookCategory);
    }

    @Override
    public BasicResponseDTO editCategory(CategoryInputDTO categoryInputDTO, Long categoryId) {
        BookCategory bookCategory = getCategory(categoryId);
        bookCategory.setName(categoryInputDTO.getName());
        bookCategory.setDescription(categoryInputDTO.getDescription());

        bookCategoryRepository.save(bookCategory);
        log.info("Category successfully updated");

        return new BasicResponseDTO(Status.SUCCESS, bookCategory);
    }

    @Override
    public BasicResponseDTO getAllCategories() {
        List<BookCategory> bookCategories = bookCategoryRepository.findAll();
        return new BasicResponseDTO(Status.SUCCESS, bookCategories);
    }

    private BookCategory getCategory(Long categoryId) {
        return bookCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Category not found!"));

    }

}
