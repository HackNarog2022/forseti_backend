package com.forseti.forseti.service;

import com.forseti.forseti.model.Category;
import com.forseti.forseti.model.Inspiration;
import com.forseti.forseti.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void saveNewCategory(String name, Set<Inspiration> inspirations) {
        this.categoryRepository.save(new Category(name, inspirations));
    }
}
