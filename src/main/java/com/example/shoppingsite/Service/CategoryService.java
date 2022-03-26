package com.example.shoppingsite.Service;

import com.example.shoppingsite.Model.Category;
import com.example.shoppingsite.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Category service.
 */
@Service
public class CategoryService {

    /**
     * The Category repository.
     */
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Create category category.
     *
     * @param category the category
     * @return the category
     */
    public Category createCategory(Category category){
       return categoryRepository.save(category);
    }

    /**
     * Get all categories list.
     *
     * @return the list
     */
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    /**
     * Update categories category.
     *
     * @param category the category
     * @param id       the id
     * @return the category
     */
    public Category updateCategories(Category category) throws Exception {
        if(category == null || category.getCategory_id()==null){
            throw new Exception("category cannot be null");
        }
        Optional<Category> optionalCategory = categoryRepository.findById(category.getCategory_id());
        if(!optionalCategory.isPresent()){
            throw new Exception("Cateory id does not exist");
        }
        Category existingCategory = optionalCategory.get();
        existingCategory.setCategory_name(category.getCategory_name());
        existingCategory.setImg_src(category.getImg_src());
        return categoryRepository.save(category);
    }

    /**
     * Find category by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<Category> findCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    /**
     * Delete categories.
     *
     * @param id the id
     */
    public void deleteCategories(String id) throws Exception{
        if(!categoryRepository.findById(id).isPresent()){
            throw new Exception("Category does not exist");
        }
        categoryRepository.deleteById(id);
    }
}
