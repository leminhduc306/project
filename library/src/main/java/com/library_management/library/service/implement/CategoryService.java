package com.library_management.library.service.implement;

import com.library_management.library.entity.Category;
import com.library_management.library.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.library_management.library.repository.CategoryRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
