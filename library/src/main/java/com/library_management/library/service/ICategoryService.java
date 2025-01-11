package com.library_management.library.service;

import com.library_management.library.entity.Category;

import java.util.List;
public interface ICategoryService {
    List<Category> getAllCategories();
    Category create(Category category);
    Category update(Category category);
    void delete(Integer id);
}
