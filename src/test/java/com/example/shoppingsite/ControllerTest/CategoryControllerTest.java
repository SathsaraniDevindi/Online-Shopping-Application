package com.example.shoppingsite.ControllerTest;

import com.example.shoppingsite.Controller.CategoryController;
import com.example.shoppingsite.Model.Category;
import com.example.shoppingsite.Service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Category controller test.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryControllerTest {

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
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

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
        this.mockMvc= MockMvcBuilders.standaloneSetup(categoryController).build();

    }

    /**
     * Create category success.
     *
     * @throws Exception the exception
     */
    @Test
    public void createCategory_success() throws Exception{
        Category category1 = new Category("110","Homeware","https://images.unsplash.com/photo-1551782450-a2132b4ba21d");

        Mockito.when(categoryService.createCategory(category1)).thenReturn(category1);

        String content = objectWriter.writeValueAsString(category1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
               // .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()));

    }

    /**
     * Gets all categories success.
     *
     * @throws Exception the exception
     */
    @Test
    public void getAllCategories_success() throws Exception{
        List<Category> categories = new ArrayList<>(Arrays.asList(CATEGORY_1,CATEGORY_2,CATEGORY_3,CATEGORY_4,CATEGORY_5));

        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/category")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(5)));
            //  .andExpect(MockMvcResultMatchers.jsonPath("$[101].category_name",is("Bakery")));
    }

    /**
     * Update category success.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateCategory_success() throws Exception {
        Category updatedCategory = new Category("100", "updated pharmacy", "updated https://images.unsplash.com/photo-1551782450-a2132b4ba21d");

        Mockito.when(categoryController.findCategoryById(CATEGORY_1.getCategory_id())).thenReturn(Optional.ofNullable(CATEGORY_1));
        Mockito.when(categoryController.createCategory(updatedCategory)).thenReturn(updatedCategory);

        String content = objectWriter.writeValueAsString(updatedCategory);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/category/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());


    }


    /**
     * Find category by id success.
     *
     * @throws Exception the exception
     */
    @Test
    public void findCategoryById_success() throws Exception{
        List<Category> categories = new ArrayList<>(Arrays.asList(CATEGORY_1,CATEGORY_2,CATEGORY_3,CATEGORY_4,CATEGORY_5));

        Mockito.when(categoryService.findCategoryById("100")).thenReturn(Optional.ofNullable(CATEGORY_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/category/Id/100")
                        .contentType(MediaType.APPLICATION_JSON))
                         .andExpect(status().isOk());
    }


    /**
     * Delete category by id success.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteCategoryById_success() throws Exception{
        Mockito.when(categoryService.findCategoryById(CATEGORY_5.getCategory_id())).thenReturn(Optional.ofNullable(CATEGORY_5));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/category/delete/104")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
