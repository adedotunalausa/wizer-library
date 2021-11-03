package com.wizer.wizerbookapp.service;

import com.wizer.wizerbookapp.dto.input.CategoryInputDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;

public interface BookCategoryService {

    BasicResponseDTO addCategory(CategoryInputDTO categoryInputDTO);

    BasicResponseDTO editCategory(CategoryInputDTO categoryInputDTO, Long categoryId);

    BasicResponseDTO getAllCategories();

}
