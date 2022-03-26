package com.example.shoppingsite.Repository;

import com.example.shoppingsite.Model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Category repository.
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {
}
