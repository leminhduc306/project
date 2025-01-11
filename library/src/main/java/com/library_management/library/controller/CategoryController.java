package com.library_management.library.controller;

import com.library_management.library.constant.ErrorCode;
import com.library_management.library.dto.Page.JsoneResponse;
import com.library_management.library.entity.Category;
import com.library_management.library.exception.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.library_management.library.service.ICategoryService;


@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {

        return JsoneResponse.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.create(category);
        return JsoneResponse.created(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        if (categoryService.getAllCategories().stream()
                .noneMatch(c -> c.getCategoryId().equals(id))) {
            throw new ApiException(ErrorCode.Category_NOT_EXIST);
        }
        category.setCategoryId(id);
        Category updatedCategory = categoryService.update(category);
        return JsoneResponse.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        if (categoryService.getAllCategories().stream()
                .noneMatch(c -> c.getCategoryId().equals(id))) {
            throw new ApiException(ErrorCode.Category_NOT_EXIST);
        }
        categoryService.delete(id);
        return JsoneResponse.noContent();
    }

}
