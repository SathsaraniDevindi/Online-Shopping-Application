package com.example.shoppingsite.ServiceTest;

import com.example.shoppingsite.Model.Category;
import com.example.shoppingsite.Repository.CategoryRepository;
import com.example.shoppingsite.Service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Category service test.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryServiceTest {

    private MockMvc mockMvc;

    /**
     * The Object mapper.
     */
    ObjectMapper objectMapper = new ObjectMapper();
    /**
     * The Object writer.
     */
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    /**
     * The Category 1.
     */
    Category CATEGORY_1 = new Category("100","Pharmacy","https://images.unsplash.com/photo-1471864190281-a93a3070b6de?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
    /**
     * The Category 2.
     */
    Category CATEGORY_2 = new Category("101","Bakery","https://images.unsplash.com/photo-1551782450-a2132b4ba21d");
    /**
     * The Category 3.
     */
    Category CATEGORY_3 = new Category("102","Beverages","https://images.unsplash.com/photo-1589948100953-963e39185fd6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1936&q=80");
    /**
     * The Category 4.
     */
    Category CATEGORY_4 = new Category("103","Dairy","https://media.istockphoto.com/photos/various-fresh-dairy-products-picture-id544807136");
    /**
     * The Category 5.
     */
    Category CATEGORY_5 = new Category("104","Fruits","https://images.unsplash.com/photo-1610832958506-aa56368176cf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");

    /**
     * Set up.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(categoryService).build();

    }
    /**
     * Create category success.
     *
     * @throws Exception the exception
     */
    @Test
    public void createCategory_success() throws Exception{
        Category category = new Category("104","Fruits","https://images.unsplash.com/photo-1610832958506-aa56368176cf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
        Mockito.when(categoryService.createCategory(category)).thenReturn(category);

        Assert.assertEquals("104", category.getCategory_id());

    }
    /**
     * Gets all categories success.
     *
     * @throws Exception the exception
     */
    @Test
    public void getAllCategories_success() throws Exception {
        List<Category> categories = new ArrayList<>(Arrays.asList(CATEGORY_1,CATEGORY_2,CATEGORY_3,CATEGORY_4,CATEGORY_5));
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> list = categoryService.getAllCategories();
        Assert.assertEquals(5, list.size());

    }

    /**
     * Find category by id success.
     *
     * @throws Exception the exception
     */
    @Test
    public void findCategoryById_success() throws Exception{
        Mockito.when(categoryService.findCategoryById("100")).thenReturn(Optional.ofNullable(CATEGORY_1));
        Mockito.when(categoryService.findCategoryById("101")).thenReturn(Optional.ofNullable(CATEGORY_2));
        Mockito.when(categoryService.findCategoryById("102")).thenReturn(Optional.ofNullable(CATEGORY_3));
        Assert.assertEquals("100", CATEGORY_1.getCategory_id());
        Assert.assertEquals("101", CATEGORY_2.getCategory_id());
        Assert.assertEquals("102", CATEGORY_3.getCategory_id());
    }



    /**
     * Update category success.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateCategory_success() throws Exception{

        Category updatedCategory = new Category("100", "updated pharmacy", "updated https://images.unsplash.com/photo-1551782450-a2132b4ba21d");

        Mockito.when(categoryService.findCategoryById(CATEGORY_1.getCategory_id())).thenReturn(Optional.ofNullable(CATEGORY_1));
        Mockito.when(categoryService.createCategory(updatedCategory)).thenReturn(updatedCategory);

        Mockito.when(categoryService.updateCategories(updatedCategory)).thenReturn(updatedCategory);
        Assert.assertEquals("updated pharmacy",CATEGORY_1.getCategory_name());
    }

    @Test
    public void deleteCategory_success() throws Exception{
        Category deleteCategory = new Category("100", "updated pharmacy", "updated https://images.unsplash.com/photo-1551782450-a2132b4ba21d");

        Mockito.when(categoryService.createCategory(deleteCategory)).thenReturn(deleteCategory);
        Mockito.when(categoryService.findCategoryById(deleteCategory.getCategory_id())).thenReturn(Optional.ofNullable(deleteCategory));

        categoryService.deleteCategories("100");
        Mockito.when(categoryService.findCategoryById(deleteCategory.getCategory_id())).thenReturn(Optional.ofNullable(deleteCategory));
        Assert.assertEquals("100",deleteCategory.getCategory_id());
    }

}
