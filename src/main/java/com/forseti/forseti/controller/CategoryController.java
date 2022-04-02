package com.forseti.forseti.controller;

import com.forseti.forseti.model.Category;
import com.forseti.forseti.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @RequestMapping(value = "/api/categories", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/api/newCategory", method = RequestMethod.POST)
    public void newCategory(String name, Set<String> inspirations) {
        categoryService.saveNewCategory(name, inspirations);
    }

//    @RequestMapping(value = "/api/saveSample", method = RequestMethod.POST)
//    public void saveCategories() {
//        saveSamples();
//    }

    private void saveSamples() {
        List.of(
                new Category("Football", Set.of(
                        "World Cup 2022",
                        "Premier League",
                        "Champions League"
                )),
                new Category("Philosophy", Set.of(
                        "History of philosophy",
                        "What is the truth?",
                        "Definition of manipulation"
                )),
                new Category("Computer Science", Set.of(
                        "Learning new programming language"
                )),
                new Category("Dancing", Set.of(
                        "How to start dancing?",
                        "Can you dance at home?"
                )),
                new Category("Politics", Set.of(
                        "Favourite party",
                        "Pros and cons of EU"
                )),
                new Category("Dogs", Set.of(
                        "Best food for dog",
                        "How to clean dog",
                        "Best toys"
                )),
                new Category("City-break", Set.of(
                        "What places to visit?",
                        "Where to eat?",
                        "How to use public transport?"
                ))
        ).forEach(
                category -> categoryService.saveNewCategory(category.getName(), category.getInspirations())
        );
    }

}
