package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Category;
import cz.cvut.kbss.ear.eshop.service.CategoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void getAllCategories() {
        List<Category> expectedCategories = Collections.singletonList(new Category());
        when(categoryService.findAll()).thenReturn(expectedCategories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        Assertions.assertEquals(expectedCategories, response.getBody());
    }

    @Test
    void updateReservation() {
        int categoryId = 1;
        Category category = new Category();
        when(categoryService.find(categoryId)).thenReturn(category);

        ResponseEntity<Void> response = categoryController.updateReservation(categoryId, category);

        Assertions.assertEquals(204, response.getStatusCodeValue());
        verify(categoryService, times(1)).update(category);
    }

    @Test
    void deleteReservation() {
        int categoryId = 1;
        Category category = new Category();
        when(categoryService.find(categoryId)).thenReturn(category);

        ResponseEntity<Void> response = categoryController.deleteReservation(categoryId);

        Assertions.assertEquals(204, response.getStatusCodeValue());
        verify(categoryService, times(1)).remove(category);
    }
}
