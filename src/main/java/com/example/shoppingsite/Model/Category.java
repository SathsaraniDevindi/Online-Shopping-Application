package com.example.shoppingsite.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Category.
 */
@Document(collection="category")
public class Category {
    @Id
    private String category_id;
    private String category_name;
    private String img_src;

    /**
     * Instantiates a new Category.
     *
     * @param category_id   the category id
     * @param category_name the category name
     * @param img_src       the img src
     */
    public Category(String category_id, String category_name, String img_src) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.img_src = img_src;
    }

    /**
     * Instantiates a new Category.
     */
    public Category() {
    }

    /**
     * Gets img src.
     *
     * @return the img src
     */
    public String getImg_src() {
        return img_src;
    }

    /**
     * Sets img src.
     *
     * @param img_src the img src
     */
    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public String getCategory_id() {
        return category_id;
    }

    /**
     * Sets category id.
     *
     * @param category_id the category id
     */
    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    /**
     * Gets category name.
     *
     * @return the category name
     */
    public String getCategory_name() {
        return category_name;
    }

    /**
     * Sets category name.
     *
     * @param category_name the category name
     */
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id='" + category_id + '\'' +
                ", category_name='" + category_name + '\'' +
                ", img_src='" + img_src + '\'' +
                '}';
    }
}
