package org.vdoloka.controller.rest;

import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.CategoryDTO;
import org.vdoloka.dto.mapper.CategoryMapper;
import org.vdoloka.repository.CategoriesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;
    @GetMapping("/categories/")
    public List<CategoryDTO> getCategories() {
        return CategoryMapper.INSTANCE.toDtoList(categoriesRepository.findAll());
    }
}