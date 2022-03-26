package com.example.shoppingsite.Controller;

import com.example.shoppingsite.Model.Category;
import com.example.shoppingsite.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Category controller.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class CategoryController {
    /**
     * The Category service.
     */
    @Autowired
    CategoryService categoryService;

    /**
     * Create category category.
     *
     * @param category the category
     * @return the category
     */
    @RequestMapping(method=RequestMethod.POST, value = "category/add")
    public Category createCategory(@RequestBody Category category){

        return categoryService.createCategory(category);
    }

    /**
     * Get all categories list.
     *
     * @return the list
     */
    @RequestMapping(method=RequestMethod.GET, value="category")
    public List<Category> getAllCategories(){
       return categoryService.getAllCategories();
    }

    /**
     * Find category by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws Exception the exception
     */
    @RequestMapping(method=RequestMethod.GET, value="category/Id/{id}")
    public Optional<Category> findCategoryById(@PathVariable String id) throws Exception{
        return categoryService.findCategoryById(id);
    }

    /**
     * Update categories category.
     *
     * @param category the category
     * @param id       the id
     * @return the category
     * @throws Exception the exception
     */
    @RequestMapping(method=RequestMethod.POST,value="category/update")
    public Category updateCategories(@RequestBody Category category) throws Exception {
        return categoryService.updateCategories(category);
    }

    /**
     * Delete categories.
     *
     * @param id the id
     */
    @RequestMapping(method = RequestMethod.DELETE,value="category/delete/{id}")
    public void deleteCategories(@PathVariable String id) throws Exception{
        categoryService.deleteCategories(id);
    }


}
