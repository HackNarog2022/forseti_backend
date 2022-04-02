package com.forseti.forseti.controller;

import com.forseti.forseti.model.Category;
import com.forseti.forseti.model.Inspiration;
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

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/newCategory", method = RequestMethod.POST)
    public void newCategory(String name, Set<Inspiration> inspirations) {
        categoryService.saveNewCategory(name, inspirations);
    }

//    @RequestMapping(value = "/saveSample", method = RequestMethod.POST)
//    public void saveCategories() {
//        saveSamples();
//    }

    private void saveSamples() {
        List.of(
                new Category("Football", Set.of(
                        new Inspiration("World Cup 2022"),
                        new Inspiration("Premier League"),
                        new Inspiration("Champions League")
                )),
                new Category("Philosophy", Set.of(
                        new Inspiration("History of philosophy"),
                        new Inspiration("What is the truth?"),
                        new Inspiration("Definition of manipulation")
                )),
                new Category("Computer Science", Set.of(
                        new Inspiration("Learning new programming language")
                )),
                new Category("Dancing", Set.of(
                        new Inspiration("How to start dancing?"),
                        new Inspiration("Can you dance at home?")
                )),
                new Category("Politics", Set.of(
                        new Inspiration("Favourite party"),
                        new Inspiration("Pros and cons of EU")
                )),
                new Category("Dogs", Set.of(
                        new Inspiration("Best food for dog"),
                        new Inspiration("How to clean dog"),
                        new Inspiration("Best toys")
                )),
                new Category("City-break", Set.of(
                        new Inspiration("What places to visit?"),
                        new Inspiration("Where to eat?"),
                        new Inspiration("How to use public transport?")
                ))
        ).forEach(
                category -> categoryService.saveNewCategory(category.getName(), category.getInspirations())
        );
    }

}
